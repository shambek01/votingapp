package com.thirdparty.votingapp.internal.controller;

import com.thirdparty.votingapp.internal.dto.PollDto;
import com.thirdparty.votingapp.internal.repository.model.Poll;
import com.thirdparty.votingapp.internal.service.InterestService;
import com.thirdparty.votingapp.internal.service.OptionService;
import com.thirdparty.votingapp.internal.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit_poll")
public class EditPollController {
    private OptionService optionService;
    private PollService pollService;
    private InterestService interestService;
    @Autowired
    public EditPollController(PollService pollService, OptionService optionService, InterestService interestService){
        this.optionService = optionService;
        this.pollService = pollService;
        this.interestService = interestService;
    }

    @GetMapping("/{id}")
    public String editPollPage(Model model,@PathVariable Long id){
        Poll poll = pollService.getById(id);
        PollDto pollDto = new PollDto();
        pollDto.setPollName(poll.getName());
        pollDto.setPollId(id);

        model.addAttribute("options",optionService.getOptionsByPollId(id));
        model.addAttribute("interests", interestService.getAll());
        model.addAttribute("dtoPoll", pollDto);
        return "edit_poll";
    }
}
