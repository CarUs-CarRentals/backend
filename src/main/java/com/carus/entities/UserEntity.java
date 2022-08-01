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

    @Column(nullable = false, length = 50)
    private String login;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 80)
    private String email;
}
