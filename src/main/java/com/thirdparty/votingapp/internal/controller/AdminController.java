package com.thirdparty.votingapp.internal.controller;

import com.thirdparty.votingapp.internal.dto.PollDto;
import com.thirdparty.votingapp.internal.repository.model.Poll;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import com.thirdparty.votingapp.internal.service.InterestService;
import com.thirdparty.votingapp.internal.service.OptionService;
import com.thirdparty.votingapp.internal.service.PollService;
import com.thirdparty.votingapp.internal.service.ProfileDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private PollService pollService;
    private OptionService optionService;
    private ProfileDetailsService profileDetailsService;
    private InterestService interestService;

    @Autowired
    public AdminController(PollService pollService, OptionService optionService, ProfileDetailsService profileDetailsService, InterestService interestService){
        this.optionService = optionService;
        this.pollService = pollService;
        this.profileDetailsService = profileDetailsService;
        this.interestService = interestService;
    }


    @GetMapping
    public String adminPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profile profile = profileDetailsService.getByUsername(authentication.getName());
        model.addAttribute("interests", interestService.getAll());
        model.addAttribute("polls", pollService.getAll());
        model.addAttribute("profileOptions", profile.getOptions());
        model.addAttribute("options", optionService.getAll());
        model.addAttribute("dtoPoll", new PollDto());
        return "admin";
    }



    @PostMapping("/poll/add")
    public String addPoll(@ModelAttribute PollDto pollDto){
        pollService.create(pollDto);
        return "redirect:/admin";
    }
    @GetMapping("/poll/delete/{id}")
    public String deletePoll(@PathVariable Long id){
        pollService.delete(id);
        return "redirect:/admin";
    }
    @GetMapping("/option/delete/{id}")
    public String deleteOption(@PathVariable Long id){
        optionService.delete(id);
        return "redirect:/admin";
    }
}
