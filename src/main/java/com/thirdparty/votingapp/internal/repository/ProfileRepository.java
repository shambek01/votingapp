package com.thirdparty.votingapp.internal.repository;

import com.thirdparty.votingapp.internal.repository.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile, Long> {
    Profile getByUsername(String username);
}
