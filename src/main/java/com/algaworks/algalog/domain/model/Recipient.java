package com.algaworks.algalog.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Recipient {

    @Column(name = "recipient_name")
    private String name;
    @Column(name = "recipient_street")
    private String street;
    @Column(name = "recipient_number")
    private String number;
    @Column(name = "recipient_complement")
    private String complement;
    @Column(name = "recipient_neighborhood")
    private String neighborhood;
}
