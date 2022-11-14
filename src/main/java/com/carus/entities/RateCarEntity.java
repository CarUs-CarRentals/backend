package com.carus.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rate_car")
@Data
public class RateCarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_RateCar_user"))
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_RateCar_car"))
    private CarEntity car;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_location"))
    private RentalEntity location;

    @Column(nullable = false, length = 10)
    private Double rate;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 20)
    private String date;

}
