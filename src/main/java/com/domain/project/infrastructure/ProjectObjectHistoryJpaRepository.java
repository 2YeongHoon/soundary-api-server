package com.domain.project.infrastructure;


import com.domain.project.domain.ProjectObjectHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectObjectHistoryJpaRepository extends JpaRepository<ProjectObjectHistory, Long> {

}
