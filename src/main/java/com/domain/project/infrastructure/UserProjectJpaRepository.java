package com.domain.project.infrastructure;


import com.domain.user.domain.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectJpaRepository extends JpaRepository<UserProject, Long> {

}
