package com.carus.entities;

import com.carus.enums.ECategory;
import com.carus.enums.EFuel;
import com.carus.enums.EGear;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "car")
@Data
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_car_user"))
    private UserEntity user;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false, length = 10)
    private String plate;

    @Enumerated
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private EFuel fuel;

    @Enumerated
    @Column(nullable = false, length = 15)
    private EGear gearShift;

    @Enumerated
    @Column(nullable = false, length = 12)
    private ECategory category;

    @Column(nullable = false, columnDefinition = "INT(11)")
    private Integer doors;

    @Column(nullable = false)
    private Integer seats;

    @Column
    private Integer trunk;

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String returnLocation;
}
