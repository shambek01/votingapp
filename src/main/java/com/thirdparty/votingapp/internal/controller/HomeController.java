package com.thirdparty.votingapp.internal.controller;

import com.thirdparty.votingapp.internal.repository.PollRepository;
import com.thirdparty.votingapp.internal.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private PollService pollService;

    @Autowired
    public HomeController(PollService pollService){
        this.pollService = pollService;
    }


    @GetMapping()
    public String homePage(Model model){
        model.addAttribute("polls", pollService.getAll());
        return "home";
    }



}
