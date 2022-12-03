package com.carus.services;

import com.carus.dto.RentalDTO;
import com.carus.entities.RentalEntity;
import com.carus.enums.ERentalStatus;
import com.carus.repositories.RentalRepository;
import com.carus.services.exceptions.EntityNotFoundException;
import com.carus.services.exceptions.InternalServerErrorException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Transactional(readOnly = true)
    public List<RentalDTO> findAll() {
        return rentalRepository.findAll().stream().map(RentalDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RentalDTO findById(Long id) {
        return new RentalDTO(rentalRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        }));
    }

    @Transactional(readOnly = true)
    public List<RentalDTO> getRentalsByUser() {
        List<RentalEntity> user = rentalRepository.findRentalsByUserUuid(userService.getLoggedUser().getUuid());
        return user.stream().map(RentalDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RentalDTO> getRentalsByCar(Long id) {
        List<RentalEntity> cars = rentalRepository.findRentalsByCarId(id);
        return cars.stream().map(RentalDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public RentalDTO save(RentalDTO dto) {
        RentalEntity entity = rentalRepository.save(this.dtoToEntity(dto));
        return new RentalDTO(entity);
    }

    @Transactional
    public RentalDTO update(RentalDTO dto, Long id) {
        if (isLate(dto)) dto.setStatus(ERentalStatus.LATE);
        RentalEntity updated = this.dtoToEntity(dto);
        updated.setId(id);
        return new RentalDTO(rentalRepository.save(updated));
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            rentalRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Entity with id {} not found", id);
            throw new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InternalServerErrorException("An internal server error has occurred, please try again later");
        }
    }

    public boolean isLate(RentalDTO rental) {
        return ERentalStatus.RENTED.equals(rental.getStatus())
                && LocalDateTime.now().isAfter(rental.getReturnDate());
    }

    private RentalEntity dtoToEntity(RentalDTO dto) {
        RentalEntity entity = new RentalEntity();
        entity.setUser(userService.findEntityByUuid(dto.getUser()));
        entity.setCar(carService.findEntityById(dto.getCar()));
        entity.setRentalDate(dto.getRentalDate());
        entity.setReturnDate(dto.getReturnDate());
        entity.setPrice(dto.getPrice());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAddress(dto.getAddress());
        entity.setStatus(dto.getStatus());
        entity.setIsReviewCar(dto.getIsReviewCar());
        entity.setIsReviewUser(dto.getIsReviewUser());

        return entity;
    }
}
