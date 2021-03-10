package com.thirdparty.votingapp.internal.repository.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "group")
@Table(name = "vote_group")
public class Group{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private long id;
    @Column(name = "group_name")
    private String name;
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<Profile> profiles;


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
}
