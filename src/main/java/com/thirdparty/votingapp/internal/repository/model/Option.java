package com.thirdparty.votingapp.internal.repository.model;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "option")
@Table(name = "vote_option")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private long id;

    @Column(name = "option_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;


    @ManyToMany(mappedBy = "options")
    private Set<Profile> profiles;


    @Transient
    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }

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
}
