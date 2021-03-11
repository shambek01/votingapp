package com.thirdparty.votingapp.internal.service;


import com.thirdparty.votingapp.internal.dto.PollDto;
import com.thirdparty.votingapp.internal.repository.OptionRepository;
import com.thirdparty.votingapp.internal.repository.PollRepository;
import com.thirdparty.votingapp.internal.repository.model.Interest;
import com.thirdparty.votingapp.internal.repository.model.Option;
import com.thirdparty.votingapp.internal.repository.model.Poll;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PollService {
    private PollRepository pollRepository;
    private OptionRepository optionRepository;
    private ProfileDetailsService profileDetailsService;

    @Autowired
    public PollService(PollRepository pollRepository,OptionRepository optionRepository, ProfileDetailsService profileDetailsService){
        this.pollRepository = pollRepository;
        this.optionRepository = optionRepository;
        this.profileDetailsService = profileDetailsService;
    }
    public void delete(Long id){
        pollRepository.delete(pollRepository.getOne(id));
    }




    public ArrayList<Poll> getAll(){
        return (ArrayList<Poll>) pollRepository.findAll();
    }


    public void update(PollDto pollDto){
            Poll poll = new Poll();
            poll.setName(pollDto.getPollName());

            if(pollDto.getPollId() != null){
                poll.setId(pollDto.getPollId());
            }
            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                Date date = formatter.parse(pollDto.getExpirationDate());
                poll.setExpirationDate(date);
            }catch(ParseException e){
                e.printStackTrace();
            }


            ArrayList<Poll> polls = (ArrayList<Poll>) pollRepository.findAll();
            polls.sort((o1, o2) -> o1.getId()<o2.getId()?1:0);




            Set<Option> options = new HashSet<>();
            if (pollDto.getOption1() != null) {
                Option option = new Option();
                option.setPoll(poll);
                option.setName(pollDto.getOption1());
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
            if(!pollDto.getInterests().isEmpty()){
                if (!options.isEmpty()) {
                    poll.setInterests(pollDto.getInterests());
                }
            }


            poll.setOptions(options);
            pollRepository.save(poll);
    }

    public void create(PollDto pollDto) {
        Poll poll = new Poll();
        poll.setName(pollDto.getPollName());
        if(pollDto.getPollId() != null){
            poll.setId(pollDto.getPollId());
        }
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Date date = formatter.parse(pollDto.getExpirationDate());
            poll.setExpirationDate(date);
        }catch(ParseException e){
            e.printStackTrace();
        }


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
        if(!pollDto.getInterests().isEmpty()){
        if (!options.isEmpty()) {
            poll.setInterests(pollDto.getInterests());
        }
        }


        poll.setOptions(options);
        pollRepository.save(poll);
    }



    public List<Poll> getAnsweredPolls(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profile profile = profileDetailsService.getByUsername(authentication.getName());


        if(profile.getOptions()!=null) {
            Set<Option> profileOptions = profile.getOptions();
            List<Poll> finalPolls = new ArrayList<>();

            for (Option option : profileOptions) {
                finalPolls.add(option.getPoll());
            }

            return finalPolls;
        }


        return new ArrayList<Poll>();

    }


    public List<Poll> getActivePolls() {
        List<Poll> polls = getAllByInterest();

        List<Poll> finalPolls = new ArrayList<>();

        List<Poll> answeredPolls = getAnsweredPolls();

        for (Poll poll : getExpiredPolls()) {
            for (Poll dbpoll : polls) {
                if (dbpoll.getId() == poll.getId()) {
                    finalPolls.remove(dbpoll);
                }
            }
        }


        for (Poll poll : answeredPolls) {
            for (Poll dbpoll : polls) {
                if (dbpoll.getId() == poll.getId()) {
                    finalPolls.remove(dbpoll);
                }
            }
        }
        LinkedHashSet<Poll> pollDelete = new LinkedHashSet<>(finalPolls);

        System.out.println(pollDelete);

        return new ArrayList<Poll>(pollDelete);

    }

    public List<Poll> getExpiredPolls(){
        List<Poll> polls = getAllByInterest();
        List<Poll> finalPolls  = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        formatter.format(date);

        System.out.println(date);
        for(Poll poll : polls){
            if(date.after(poll.getExpirationDate())){
                finalPolls.add(poll);
                System.out.println(poll.getExpirationDate());
            }
        }
        return finalPolls;


    }


    public List<Poll> getAllByInterest(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profile profile = profileDetailsService.getByUsername(authentication.getName());
        Set<Interest> profileInterests = profile.getInterests();
        List<Poll> polls = pollRepository.findAll();
        List<Poll> finalPolls = new ArrayList<>();
        for(Poll poll : polls){
            for(Interest pollInterest : poll.getInterests()){
                for(Interest profileInterest : profileInterests){
                    if(pollInterest.getId()==profileInterest.getId()){
                        finalPolls.add(poll);
                    }
                }
            }
        }
        LinkedHashSet<Poll> pollDelete = new LinkedHashSet<>(finalPolls);

        System.out.println(pollDelete);

        return new ArrayList<Poll>(pollDelete);


    }

    public Poll getPollById(long id){
        return pollRepository.findById(id).get();


    }
    public Poll getById(Long id) {
        return pollRepository.getOne(id);
    }
}

