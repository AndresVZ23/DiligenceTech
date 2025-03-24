package com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.valueobjects;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.entities.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class MembersList {

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public List<Member> members;

    public void addMember(Member member) {
        this.members.add(member);
    }

    public MembersList() {
        this.members = new ArrayList<>();
    }
}