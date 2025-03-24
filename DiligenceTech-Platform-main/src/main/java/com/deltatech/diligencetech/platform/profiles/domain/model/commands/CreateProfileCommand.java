package com.deltatech.diligencetech.platform.profiles.domain.model.commands;

public record CreateProfileCommand(String username, Long userId, String email, String firstName, String lastName) {
}
