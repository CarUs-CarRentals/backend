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

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(length = 60)
    private String gender;

    @Column(nullable = false, length = 20)
    private String phone;

}
