

package com.service.acmedeliveryfinal.repository;


import com.service.acmedeliveryfinal.domain.Role;
import com.service.acmedeliveryfinal.domain.enumeration.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
