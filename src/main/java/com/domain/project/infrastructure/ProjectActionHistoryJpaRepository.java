package com.domain.project.infrastructure;


import com.domain.project.domain.ProjectActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectActionHistoryJpaRepository extends JpaRepository<ProjectActionHistory, Long> {

}
