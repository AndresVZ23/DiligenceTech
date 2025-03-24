package com.deltatech.diligencetech.platform.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        List<String> roles) {
}
