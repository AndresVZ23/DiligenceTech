package com.deltatech.diligencetech.platform.iam.application.internal.commandservices;

import com.deltatech.diligencetech.platform.iam.application.internal.outboundservices.acl.IamExternalsService;
import com.deltatech.diligencetech.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.deltatech.diligencetech.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.deltatech.diligencetech.platform.iam.domain.model.aggregates.User;
import com.deltatech.diligencetech.platform.iam.domain.model.commands.SignInCommand;
import com.deltatech.diligencetech.platform.iam.domain.model.commands.SignUpCommand;
import com.deltatech.diligencetech.platform.iam.domain.services.UserCommandService;
import com.deltatech.diligencetech.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.deltatech.diligencetech.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final IamExternalsService iamExternalsService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository, IamExternalsService iamExternalsService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.iamExternalsService = iamExternalsService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.email())) {
            throw new IllegalArgumentException("User already exists");
        }
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        var user = new User(command.email(), hashingService.encode(command.password()));
        roles.forEach(user::addRole);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }

        var profileId = iamExternalsService.createProfileByUsername(command, user.getId());
        if (profileId.isEmpty()) {
            // rollback user creation
            userRepository.delete(user);
            throw new IllegalArgumentException("Unable to create Profile");
        }

        return userRepository.findByUsername(command.email());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(command.password(), user.getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.getUserEmail());
        return Optional.of(new ImmutablePair<>(user, token));
    }

}