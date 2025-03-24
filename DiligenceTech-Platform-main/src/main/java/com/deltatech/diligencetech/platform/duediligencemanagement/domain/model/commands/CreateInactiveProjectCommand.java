package com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.commands;

import java.util.List;

//old
//public record CreateProjectCommand(String projectFullName, String managerFirstName, String managerLastName, String startDate, String endDate, Float budget, Long progress, String status) {
//}
//new
public record CreateInactiveProjectCommand(String name, List<String> agentsUsernames, List<Boolean> agentsRoles) {
}
