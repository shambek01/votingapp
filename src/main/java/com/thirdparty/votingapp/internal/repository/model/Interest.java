package com.thirdparty.votingapp.internal.repository.model;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "interest")
@Table(name = "vote_interest")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private long id;
    @Column(name = "interest_name")
    private String name;
    @ManyToMany(mappedBy = "interests")
    private Set<Profile> profiles;
    @ManyToMany(mappedBy = "interests")
    private Set<Poll> polls;


    public void setId(long id) {
        this.id = id;
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

    public Set<Poll> getPolls() {
        return polls;
    }

    public void setPolls(Set<Poll> polls) {
        this.polls = polls;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
