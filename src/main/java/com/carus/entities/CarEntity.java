package com.carus.entities;

import com.carus.enums.ECategory;
import com.carus.enums.EFuel;
import com.carus.enums.EGear;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
@Data
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", foreignKey = @ForeignKey(name = "fk_car_user"))
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

    @Column(nullable = false)
    private Integer trunk;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,8)")
    private BigDecimal latitude;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,8)")
    private BigDecimal longitude;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double price;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "car_image",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<ImageEntity> carImages = new ArrayList<>(0);

    @Column(nullable = false, columnDefinition = "BIT")
    private Boolean active = true;
}
