package com.thirdparty.votingapp.internal.repository;

import com.thirdparty.votingapp.internal.repository.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
