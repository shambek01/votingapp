package com.thirdparty.votingapp.internal.controller;

import com.thirdparty.votingapp.internal.repository.PollRepository;
import com.thirdparty.votingapp.internal.repository.model.Option;
import com.thirdparty.votingapp.internal.repository.model.Poll;
import com.thirdparty.votingapp.internal.service.OptionService;
import com.thirdparty.votingapp.internal.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {
    private PollService pollService;
    private OptionService optionService;

    @Autowired
    public HomeController(PollService pollService, OptionService optionService){
        this.pollService = pollService;
        this.optionService = optionService;
    }


    @GetMapping()
    public String homePage(Model model){
        if(isLogged()) {

            model.addAttribute("answeredPolls", handler(pollService.getAnsweredPolls()));
            model.addAttribute("activePolls", pollService.getActivePolls());
            model.addAttribute("expiredPolls", handler(pollService.getActivePolls()));
        }
        return "home";
    }

    public List<Poll> handler(List<Poll> polls){
        for (Poll poll: polls){
            int counter = 0;
            ArrayList<Option> options = optionService.getOptionsByPollId(poll.getId());
            for(Option option : options){
                counter = counter + option.getProfiles().size();
            }
            for(Option option: options){
                option.setCounter(counter);
            }

            poll.setOptions(new HashSet<Option>(options));
        }
        return polls;
    }




    public static boolean isLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }



}
