package com.carus.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "car")
@Data
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Column(name = "user_id")
    private UserEntity user;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, length = 4)
    private String ano;

    @Column(nullable = false, length = 15)
    private String cor;

    @Column(nullable = false, length = 10)
    private String placa;

    @Column(nullable = false, length = 15)
    private String combustivel;

    @Column(nullable = false, length = 15)
    private String cambio;

    @Column(nullable = false, length = 10)
    private String portas;

    @Column(nullable = false, length = 10)
    private String lugares;

    @Column(nullable = false, length = 10)
    private String malas;
}
