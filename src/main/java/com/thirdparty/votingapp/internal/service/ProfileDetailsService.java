package com.thirdparty.votingapp.internal.service;

import com.thirdparty.votingapp.internal.repository.ProfileRepository;
import com.thirdparty.votingapp.internal.repository.RoleRepository;
import com.thirdparty.votingapp.internal.repository.model.Group;
import com.thirdparty.votingapp.internal.repository.model.Profile;
import com.thirdparty.votingapp.internal.repository.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class ProfileDetailsService implements UserDetailsService {
    private ProfileRepository profileRepository;
    private RoleRepository roleRepository;

    @Autowired
    public ProfileDetailsService(ProfileRepository profileRepository, RoleRepository roleRepository){
        this.profileRepository = profileRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileRepository.getByUsername(username);
        if(profile == null){
            throw new UsernameNotFoundException("Account Not Found Exception is thrown");
        }
        return profile;
    }



    public boolean register(Profile profile){
        System.out.println("SUKAAAA" + profile);
        Profile dbProfile = profileRepository.getByUsername(profile.getUsername());
        System.out.println("DBSUKAAAA" + dbProfile);
        if(dbProfile==null){
            profile.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
            profile.setPassword(new BCryptPasswordEncoder().encode(profile.getPassword()));
            profileRepository.save(profile);
            return true;
        }else{
            return false;
        }
    }


    public Profile getByUsername(String username){
        return profileRepository.getByUsername(username);
    }


    public void update(Profile profile) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Profile profileDb = getByUsername(authentication.getName());

        if(profile.getFirstname()!=null){
            profileDb.setFirstname(profile.getFirstname());
        }
        if(profile.getUsername()!=null){
            profileDb.setUsername(profile.getUsername());
        }
        if(profile.getBirthDate()!=null){
            profileDb.setBirthDate(profile.getBirthDate());
        }
        if(profile.getLastname()!=null){
            profileDb.setLastname(profile.getLastname());
        }
        if(profile.getGender()!=null){
            profileDb.setGender(profile.getGender());
        }
        if(profile.getGroup()!=null){
            profileDb.setGroup(new Group());
            profileDb.getGroup().setId(profile.getGroup().getId());
        }
        profileRepository.save(profileDb);

    }

    public void addInterest(Profile profileDb) {
        profileRepository.save(profileDb);
    }
}
