package com.carus.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "location")
@Data
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_locator_user"))
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_located_car"))
    private CarEntity car;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime locationDate = LocalDateTime.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime returnDate = LocalDateTime.now();

    @Column(nullable = false, length = 11)
    private Double price;

    @Column(nullable = false, length = 11)
    private Long latitude;

    @Column(nullable = false, length = 11)
    private Long longitude;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 11)
    private String status;

    @Column(nullable = false, length = 5)
    private Boolean isReview = false;

}
