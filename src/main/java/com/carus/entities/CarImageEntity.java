package com.carus.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
public class CarImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Url;
}
