package com.thirdparty.votingapp.internal.service;


import com.thirdparty.votingapp.internal.dto.PollDto;
import com.thirdparty.votingapp.internal.repository.OptionRepository;
import com.thirdparty.votingapp.internal.repository.PollRepository;
import com.thirdparty.votingapp.internal.repository.model.Option;
import com.thirdparty.votingapp.internal.repository.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PollService {
    private PollRepository pollRepository;
    private OptionRepository optionRepository;

    @Autowired
    public PollService(PollRepository pollRepository,OptionRepository optionRepository){
        this.pollRepository = pollRepository;
        this.optionRepository = optionRepository;
    }




    public ArrayList<Poll> getAll(){
        return (ArrayList<Poll>) pollRepository.findAll();
    }




    public void create(PollDto pollDto) {
        Poll poll = new Poll();
        poll.setName(pollDto.getPollName());
        poll.setExpirationDate(pollDto.getExpirationDate());

        ArrayList<Poll> polls = (ArrayList<Poll>) pollRepository.findAll();
        polls.sort((o1, o2) -> o1.getId()<o2.getId()?1:0);




        Set<Option> options = new HashSet<>();
        if (pollDto.getOption1() != null) {
            Option option = new Option();
            option.setPoll(poll);
            option.setName(pollDto.getOption1());
            options.add(option);
        }
        if (pollDto.getOption2() != null) {
            Option option = new Option();
            option.setPoll(poll);
            option.setName(pollDto.getOption2());
            options.add(option);
        }
        if (pollDto.getOption3() != null) {
            Option option = new Option();
            option.setPoll(poll);
            option.setName(pollDto.getOption3());
            options.add(option);
            System.out.println(option.getName());
        }
        if (pollDto.getOption4() != null) {
            Option option = new Option();
            option.setPoll(poll);
            option.setName(pollDto.getOption4());
            options.add(option);
        }
        if (pollDto.getOption5() != null) {
            Option option = new Option();
            option.setPoll(poll);
            option.setName(pollDto.getOption5());
            options.add(option);
        }
        if (pollDto.getOption6() != null) {
            Option option = new Option();
            option.setPoll(poll);
            option.setName(pollDto.getOption6());

            options.add(option);
        }
        if (!options.isEmpty()) {
            poll.setOptions(options);
            pollRepository.save(poll);
        }
    }






    }

