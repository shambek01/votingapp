package com.thirdparty.votingapp.internal.controller;


import com.thirdparty.votingapp.internal.repository.model.Interest;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import com.thirdparty.votingapp.internal.service.GroupService;
import com.thirdparty.votingapp.internal.service.InterestService;
import com.thirdparty.votingapp.internal.service.ProfileDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private ProfileDetailsService profileDetailsService;
    private GroupService groupService;
    private InterestService interestService;


    @Autowired
    public ProfileController(@Qualifier("profileDetailsService")ProfileDetailsService profileDetailsService,
                             GroupService groupService, InterestService interestService){
        this.profileDetailsService = profileDetailsService;
        this.groupService = groupService;
        this.interestService = interestService;
    }


    @GetMapping("/new")
    public String registrationHandler(Model model){
        model.addAttribute("profile", new Profile());
        model.addAttribute("groups", groupService.getAll());
        return "registration";
    }

    @PostMapping("/new/register")
    public String register(@ModelAttribute Profile profile){
        profileDetailsService.register(profile);
        return "redirect:/home";
    }


    @GetMapping()
    public String profilePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("profile", profileDetailsService.getByUsername(authentication.getName()));
        return "account";
    }


    @GetMapping("/edit")
    public String editProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profile profile = profileDetailsService.getByUsername(authentication.getName());
        Long interestId = 0L;
        model.addAttribute("interestId", interestId);
        model.addAttribute("interests", interestService.getAllProfileUpdate(profile));
        model.addAttribute("profileForm", new Profile());
        model.addAttribute("userInterests", profile.getInterests());
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("profile", profile);
        return "account_edit";
    }


    @PostMapping("/update")
    public String updateProfile(@ModelAttribute Profile profile){
        profileDetailsService.update(profile);
        return "redirect:/logout";
    }


    @PostMapping("/update/interest")
    public String updateInterest(@ModelAttribute("interestId") long id){
        System.out.println(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profile profileDb = profileDetailsService.getByUsername(authentication.getName());
        Interest interest = new Interest();
        interest.setId(id);
        Set<Interest> interestSet = profileDb.getInterests();
        interestSet.add(interest);
        profileDb.setInterests(interestSet);
        profileDetailsService.addInterest(profileDb);
        return "redirect:/profile";
    }



}
