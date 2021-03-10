package com.thirdparty.votingapp.internal.repository.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Profile> profiles;


    public Role(long id, String name){
        this.id = id;
        this.name = name;
    }
    public Role(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }



    @Override
    public String getAuthority() {
        return this.name;
    }
}
