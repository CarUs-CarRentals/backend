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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_address_user"))
    private UserEntity user;

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
    private int number;
}
