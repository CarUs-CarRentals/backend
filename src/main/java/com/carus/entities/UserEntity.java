package com.carus.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 60)
    private String gender;

}
