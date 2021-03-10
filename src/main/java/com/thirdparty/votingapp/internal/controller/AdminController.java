package com.thirdparty.votingapp.internal.controller;

import com.thirdparty.votingapp.internal.dto.PollDto;
import com.thirdparty.votingapp.internal.repository.model.Poll;
import com.thirdparty.votingapp.internal.service.OptionService;
import com.thirdparty.votingapp.internal.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private PollService pollService;
    private OptionService optionService;

    @Autowired
    public AdminController(PollService pollService, OptionService optionService){
        this.optionService = optionService;
        this.pollService = pollService;
    }


    @GetMapping
    public String adminPage(Model model){
        model.addAttribute("polls", pollService.getAll());
        model.addAttribute("options", optionService.getAll());
        model.addAttribute("dtoPoll", new PollDto());
        return "admin";
    }



    @PostMapping("/poll/add")
    public String addPoll(@ModelAttribute PollDto pollDto){
        pollService.create(pollDto);
        return "redirect:/admin";
    }
}
