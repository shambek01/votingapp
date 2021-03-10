package com.thirdparty.votingapp.internal.service;

import com.thirdparty.votingapp.internal.repository.OptionRepository;
import com.thirdparty.votingapp.internal.repository.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OptionService {
    private OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository){
        this.optionRepository = optionRepository;
    }

    public ArrayList<Option> getAll(){
        ArrayList<Option> options = (ArrayList<Option>) optionRepository.findAll();
        options.sort((o1, o2) -> o1.getPoll().getId()<o2.getPoll().getId()?1:0);
        return options;
    }



}
