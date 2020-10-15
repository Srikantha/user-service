package com.ing.assignment.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="street", length=100, nullable=true, unique=false)
    private String street;

    @Column(name="city", length=100, nullable=true, unique=false)
    private String city;

    @Column(name="state", length=20, nullable=true, unique=false)
    private String state;

    @Column(name="post_code", length=10, nullable=true, unique=false)
    private String postCode;
}
