package com.neylandev.delivery.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
