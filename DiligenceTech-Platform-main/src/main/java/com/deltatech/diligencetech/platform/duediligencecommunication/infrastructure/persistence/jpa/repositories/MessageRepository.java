package com.deltatech.diligencetech.platform.duediligencecommunication.infrastructure.persistence.jpa.repositories;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByProjectId(Long projectId);
}