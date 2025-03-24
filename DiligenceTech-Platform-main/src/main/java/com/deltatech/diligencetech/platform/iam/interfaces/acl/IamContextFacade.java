package com.deltatech.diligencetech.platform.iam.interfaces.acl;

import com.deltatech.diligencetech.platform.iam.domain.model.queries.GetUserByEmailQuery;
import com.deltatech.diligencetech.platform.iam.domain.services.UserCommandService;
import com.deltatech.diligencetech.platform.iam.domain.services.UserQueryService;
import com.deltatech.diligencetech.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IamContextFacade {
    private final UserQueryService userQueryService;

    public IamContextFacade(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    public Optional<Long> fetchUserByUsername(String username) {
        var command = new GetUserByEmailQuery(username);
        var user = userQueryService.handle(command);
        return user.map(AuditableAbstractAggregateRoot::getId);
    }

    public List<Long> fetchUsersIdByUsernames(List<String> usernames) {
        return usernames.stream()
                .map(this::fetchUserByUsername)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
