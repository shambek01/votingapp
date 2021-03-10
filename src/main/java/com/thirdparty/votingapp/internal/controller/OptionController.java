package com.thirdparty.votingapp.internal.controller;


import com.thirdparty.votingapp.internal.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/option")
public class OptionController{
    private OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService){
        this.optionService = optionService;
    }


    @GetMapping("/vote")
    public String vote(@RequestParam("voteId") long id){
        optionService.vote(id);
        return "redirect:/";
    }





}
