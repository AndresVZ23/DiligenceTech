package com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.entities.Member;
import com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.rest.resources.MemberResource;

public class MemberResourceFromEntityAssembler {

    public static MemberResource toResourceFromEntity(Member entity) {
        return new MemberResource(entity.getId(), entity.getProject().getId(), entity.getUserId(), entity.getAgentRole().toString());
    }
}