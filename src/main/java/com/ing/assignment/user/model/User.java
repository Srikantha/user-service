package com.ing.assignment.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title", length=10, nullable=true, unique=false)
    private String title;

    @Column(name="first_name", length=100, nullable=false, unique=false)
    private String firstName;

    @Column(name="last_name", length=100, nullable=false, unique=false)
    private String lastName;

    @Column(name="gender", length=10, nullable=true, unique=false)
    private String gender;

    @Column(name="email_id", length=100, nullable=false, unique=true)
    private String emailId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
