package com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.rest.resources;

import java.util.List;

//old
//public record CreateProjectResource(String projectFullName, String managerFirstName, String managerLastName, String startDate, String endDate, Float budget, Long progress, String status) {
//}
//new
public record CreateProjectResource(String name, List<String> agentsUsernames, List<Boolean> agentsRoles) {
}
