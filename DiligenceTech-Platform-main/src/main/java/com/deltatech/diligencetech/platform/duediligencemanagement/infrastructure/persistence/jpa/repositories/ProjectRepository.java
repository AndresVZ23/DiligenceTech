package com.deltatech.diligencetech.platform.duediligencemanagement.infrastructure.persistence.jpa.repositories;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.aggregates.Project;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT DISTINCT a FROM Project a JOIN a.membersList.members b WHERE b.userId = :value")
    List<Project> findAllByMembersSpecificUserId(@Param("value") Long userId);

    @Query("SELECT DISTINCT a FROM Project a JOIN a.membersList.members b WHERE b.userId = :value AND a.active = true")
    List<Project> findAllActiveByMembersSpecificUserId(@Param("value") Long userId);

    @Query("SELECT DISTINCT a FROM Project a JOIN a.membersList.members b WHERE b.userId = :value AND a.active = false")
    List<Project> findAllCompleteByMembersSpecificUserId(@Param("value") Long userId);
}