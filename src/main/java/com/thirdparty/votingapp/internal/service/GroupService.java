package com.thirdparty.votingapp.internal.service;

import com.thirdparty.votingapp.internal.repository.GroupRepository;
import com.thirdparty.votingapp.internal.repository.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupService {
    private GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }


    public ArrayList<Group> getAll(){
        return  (ArrayList<Group>)groupRepository.findAll();
    }




}
