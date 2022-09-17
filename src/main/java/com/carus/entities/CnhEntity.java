package com.carus.entities;

import com.carus.enums.StateEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cnh")
@Data
public class CnhEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cnh_user"))
    private UserEntity user;

    @Column(nullable = false, length = 8)
    private String rg;

    @Column(nullable = false, length = 11)
    private String registerNumber;

    @Column(nullable = false, length = 10)
    private String cnhNumber;

    @Column(nullable = false, length = 10)
    private String expirationDate;

    @Column(nullable = false, length = 10)
    private String birthDate;

    @Enumerated
    @Column(nullable = false)
    private StateEnum stateEnum;
}
