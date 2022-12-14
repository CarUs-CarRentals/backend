package com.carus.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rate_car")
@Data
public class RateCarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", foreignKey = @ForeignKey(name = "fk_user_evaluator"))
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_car_evaluated"))
    private CarEntity car;

    @OneToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_rental"), nullable = false)
    private RentalEntity rental;

    @Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
    private Double rate;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

}
