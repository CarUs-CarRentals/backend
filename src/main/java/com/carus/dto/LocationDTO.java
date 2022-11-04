package com.carus.dto;

import com.carus.entities.LocationEntity;
import com.carus.enums.ELocationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {

    private Long id;
    private Long user;
    private Long car;
    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime locationDate;
    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime returnDate;
    private Double price;
    private Long latitude;
    private Long longitude;
    private String address;
    private ELocationStatus status;
    private Boolean isReview = false;

    public LocationDTO(LocationEntity entity) {
        this.id = entity.getId();
        this.user = entity.getUser().getId();
        this.car = entity.getCar().getId();
        this.locationDate = entity.getLocationDate();
        this.returnDate = entity.getReturnDate();
        this.price = entity.getPrice();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.address = entity.getAddress();
        this.status = entity.getStatus();
        this.isReview = entity.getIsReview();
    }
}
