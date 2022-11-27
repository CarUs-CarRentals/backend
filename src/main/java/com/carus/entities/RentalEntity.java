package com.carus.entities;

import com.carus.enums.ERentalStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
@Data
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", foreignKey = @ForeignKey(name = "fk_locator_user"))
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_located_car"))
    private CarEntity car;

    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime rentalDate;

    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime returnDate;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double price;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,8)")
    private BigDecimal latitude;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,8)")
    private BigDecimal longitude;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 11)
    private ERentalStatus status;

    @Column(nullable = false, columnDefinition = "BIT")
    private Boolean isReviewCar = false;

    @Column(nullable = false, columnDefinition = "BIT")
    private Boolean isReviewUser = false;

}
