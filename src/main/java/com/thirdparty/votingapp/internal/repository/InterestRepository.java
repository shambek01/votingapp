package com.thirdparty.votingapp.internal.repository;

import com.thirdparty.votingapp.internal.repository.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
}
