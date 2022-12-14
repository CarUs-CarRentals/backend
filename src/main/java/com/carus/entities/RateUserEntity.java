package com.carus.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rate_user")
@Data
public class RateUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rated_user_uuid", referencedColumnName = "uuid", foreignKey = @ForeignKey(name = "fk_rated_user"))
    private UserEntity ratedUser;

    @OneToOne
    @JoinColumn(name = "evaluator_user_uuid", referencedColumnName = "uuid", foreignKey = @ForeignKey(name = "fk_evaluator_user"))
    private UserEntity evaluatedUser;

    @OneToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_rental_id"), nullable = false)
    private RentalEntity rental;

    @Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
    private Double rate;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDate date;
}
