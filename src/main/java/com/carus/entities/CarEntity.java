package com.carus.entities;

import com.carus.enums.ECategory;
import com.carus.enums.EFuel;
import com.carus.enums.EGear;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private EFuel fuel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private EGear gearShift;

    @Enumerated(EnumType.STRING)
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
