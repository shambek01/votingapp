package com.thirdparty.votingapp.internal.repository.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity(name = "poll")
@Table(name = "vote_poll")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poll_id")
    private long id;

    @Column(name = "poll_name")
    private String name;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @OneToMany(mappedBy = "poll", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Option> options;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Poll_Interests",
            joinColumns = { @JoinColumn(name = "poll_id") },
            inverseJoinColumns = { @JoinColumn(name = "interest_id") }
    )
    private Set<Interest> interests;



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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }


    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }
}
