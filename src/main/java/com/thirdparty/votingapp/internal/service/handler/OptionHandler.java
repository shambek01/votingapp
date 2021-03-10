package com.thirdparty.votingapp.internal.service.handler;

import com.thirdparty.votingapp.internal.repository.OptionRepository;
import com.thirdparty.votingapp.internal.repository.ProfileRepository;
import com.thirdparty.votingapp.internal.repository.model.Option;
import com.thirdparty.votingapp.internal.repository.model.Profile;

import java.util.Set;

public class OptionHandler extends Thread {
    private ProfileRepository profileRepository;
    private String username;
    private long optionId;

    public OptionHandler(ProfileRepository profileRepository, String username, long optionId){
        this.profileRepository = profileRepository;
        this.username = username;
        this.optionId = optionId;
        start();
    }



    @Override
    public void run(){
        Profile profile = profileRepository.getByUsername(username);
        Set<Option> profileOptions = profile.getOptions();
        Option option = new Option();
        option.setId(optionId);
        profileOptions.add(option);
        profile.setOptions(profileOptions);
        profileRepository.save(profile);
    }



}
