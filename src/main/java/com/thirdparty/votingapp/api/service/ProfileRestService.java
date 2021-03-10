package com.thirdparty.votingapp.api.service;
import com.thirdparty.votingapp.internal.repository.ProfileRepository;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileRestService {
    private ProfileRepository profileRepository;

    @Autowired
    public ProfileRestService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }


    public Profile getByUsername(String username){
        return profileRepository.getByUsername(username);
    }



}
