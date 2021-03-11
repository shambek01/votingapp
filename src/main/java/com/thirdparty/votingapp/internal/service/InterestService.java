package com.thirdparty.votingapp.internal.service;

import com.thirdparty.votingapp.internal.repository.InterestRepository;
import com.thirdparty.votingapp.internal.repository.model.Interest;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class InterestService {
    private InterestRepository interestRepository;


    @Autowired
    public InterestService(InterestRepository interestRepository){
        this.interestRepository = interestRepository;
    }


    public ArrayList<Interest> getAll(){
        return (ArrayList<Interest>) interestRepository.findAll();
    }


    public HashSet<Interest> getAllProfileUpdate(Profile profile){
        HashSet<Interest> userInterests = new HashSet<>(profile.getInterests());
        ArrayList<Interest> interests = getAll();
        HashSet<Interest> profileUpdateInterests = new HashSet<>();
        System.out.println(userInterests);
        System.out.println(interests);
        for(Interest interest: interests){
            for(Interest userInterest : userInterests){
                if(interest.getId() != userInterest.getId()){
                    profileUpdateInterests.add(interest);
                }
            }
            if(userInterests.isEmpty()){
                profileUpdateInterests.add(interest);
            }
        }
        System.out.println(profileUpdateInterests);
        return profileUpdateInterests;

    }


    public void add(Interest interest){
        interestRepository.save(interest);
    }


    public void delete(Long id) {
        interestRepository.delete(interestRepository.getOne(id));
    }
}
