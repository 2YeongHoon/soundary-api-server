package com.domain.policy.infrastructure;


import com.domain.policy.domain.Policy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PolicyJpaRepository extends JpaRepository<Policy, Long> {

    @Query(value = "SELECT * FROM policy e WHERE e.version = (SELECT MAX(sub.version) FROM policy sub WHERE sub.code = e.code)", nativeQuery = true)
    List<Policy> getLatestPolicyList();

}
