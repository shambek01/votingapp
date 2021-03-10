package com.thirdparty.votingapp.api.rest;

import com.thirdparty.votingapp.api.service.ProfileRestService;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileRestController {
    private ProfileRestService profileRestService;

    @Autowired
    public ProfileRestController(ProfileRestService profileRestService){
        this.profileRestService = profileRestService;
    }


    @GetMapping(value = "/{username}", produces = "application/json")
    public Profile getByUsername(@PathVariable String username){
        return profileRestService.getByUsername(username);
    }










}
