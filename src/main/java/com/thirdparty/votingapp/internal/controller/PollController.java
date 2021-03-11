package com.thirdparty.votingapp.internal.controller;


import com.thirdparty.votingapp.internal.repository.model.Option;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import com.thirdparty.votingapp.internal.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/poll")
public class PollController {
    private PollService pollService;

    @Autowired
    public PollController(PollService pollService){
        this.pollService = pollService;
    }



    @GetMapping("/{id}")
    public String getPoll(@PathVariable long id, Model model){
        if(isLogged()){

            Set<Option> pollOptions = pollService.getPollById(id).getOptions();
            model.addAttribute("poll", pollService.getPollById(id));

            List<Profile> profiles = new ArrayList<>();
            for (Option option: pollOptions) {
                for(Profile profile: option.getProfiles()){
                    Set<Option> options = new HashSet<>();
                    options.add(option);
                    Profile newProfile = new Profile();
                    newProfile = profile;
                    newProfile.setOptions(options);
                    profiles.add(newProfile);
                }
            }
            model.addAttribute("profileList", profiles);
        }



        return "poll_statistics";

    }



    public static boolean isLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }



}
