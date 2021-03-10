package com.thirdparty.votingapp.internal.repository;

import com.thirdparty.votingapp.internal.repository.model.Option;
import com.thirdparty.votingapp.internal.repository.model.Poll;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OptionRepository  extends JpaRepository<Option, Long> {
    List<Option> findAllByPollId(long id);
}
