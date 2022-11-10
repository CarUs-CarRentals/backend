package com.carus.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rate_user")
@Data
public class RateUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rated_user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_rated_user"))
    private UserEntity ratedUser;

    @OneToOne
    @JoinColumn(name = "evaluator_user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_evaluator_user"))
    private UserEntity evaluatorUser;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double rate;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 20)
    private String date;
}
