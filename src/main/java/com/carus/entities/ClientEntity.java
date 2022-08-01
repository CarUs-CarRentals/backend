package com.carus.entities;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 60)
    private String gender;

    @Column(length = 20, nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "id")
    private UserEntity idUser;
}
