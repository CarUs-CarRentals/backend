package com.carus.dto;

import com.carus.entities.RentalEntity;
import com.carus.enums.ERentalStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {

    private Long id;
    private String user;
    private Long car;
    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime rentalDate;
    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime returnDate;
    private Double price;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private ERentalStatus status;
    private Boolean isReview = false;

    public RentalDTO(RentalEntity entity) {
        this.id = entity.getId();
        this.user = entity.getUser().getUuid();
        this.car = entity.getCar().getId();
        this.rentalDate = entity.getRentalDate();
        this.returnDate = entity.getReturnDate();
        this.price = entity.getPrice();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.status = entity.getStatus();
        this.isReview = entity.getIsReview();
    }
}
