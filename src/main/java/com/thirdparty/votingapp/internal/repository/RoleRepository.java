package com.thirdparty.votingapp.internal.repository;

import com.thirdparty.votingapp.internal.repository.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
