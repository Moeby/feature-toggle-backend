package com.moeby.featuretoggle.service.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person implements Serializable { // Comment: I'm aware that user would have been a better name but because it's a reserved word in H2 I used person

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(columnDefinition = "CHAR(36)", name = "person_id")
    private UUID personId;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private int role;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "people")
    private final List<FeatureToggle> featureToggles = new ArrayList<>();

    public UUID getPersonId() {
        return personId;
    }
    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public List<FeatureToggle> getFeatureToggles() {
        return featureToggles;
    }


}
