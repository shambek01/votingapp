package com.thirdparty.votingapp.internal.service;

import com.thirdparty.votingapp.internal.repository.OptionRepository;
import com.thirdparty.votingapp.internal.repository.ProfileRepository;
import com.thirdparty.votingapp.internal.repository.model.Option;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import com.thirdparty.votingapp.internal.service.handler.OptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class OptionService {
    private OptionRepository optionRepository;
    private ProfileRepository profileRepository;
    private ProfileDetailsService profileDetailsService;

    @Autowired
    public OptionService(OptionRepository optionRepository, ProfileDetailsService profileDetailsService, ProfileRepository profileRepository){
        this.optionRepository = optionRepository;
        this.profileDetailsService = profileDetailsService;
        this.profileRepository = profileRepository;
    }

    public ArrayList<Option> getAll(){
        ArrayList<Option> options = (ArrayList<Option>) optionRepository.findAll();
        options.sort((o1, o2) -> o1.getPoll().getId()<o2.getPoll().getId()?1:0);
        return options;
    }


    public void vote(long voteId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new OptionHandler(profileRepository, authentication.getName(), voteId);
    }


    public ArrayList<Option> getOptionsByPollId(long id){
        return (ArrayList<Option>) optionRepository.findAllByPollId(id);
    }
    public void delete(Long id){
        optionRepository.delete(optionRepository.getOne(id));
    }



}
