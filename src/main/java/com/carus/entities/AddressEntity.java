package com.carus.entities;

import com.carus.enums.EState;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String cep;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EState state;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String neighborhood;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false, length = 5)
    private Integer number;
}
