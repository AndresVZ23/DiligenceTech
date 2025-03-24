package com.deltatech.diligencetech.platform.duediligencemanagement.infrastructure.persistence.jpa.repositories;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { }
