package com.carus.services;

import com.carus.dto.AddressDTO;
import com.carus.entities.AddressEntity;
import com.carus.repositories.AddressRepository;
import com.carus.services.exceptions.EntityAlreadyExistsException;
import com.carus.services.exceptions.EntityNotFoundException;
import com.carus.services.exceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public AddressDTO create(AddressEntity address) {
        return new AddressDTO(addressRepository.save(address));
    }

    @Transactional(readOnly = true)
    public AddressDTO findById(Long id) {return new AddressDTO(this.findEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream().map(AddressDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AddressDTO findByUserUuid(String userUuid) {
        return new AddressDTO(addressRepository.findAddressByUserId(userUuid));
    }

    @Transactional(readOnly = true)
    public AddressEntity findEntityById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> {
            log.error("Entity with id {} not found", id);
            return new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        });
    }

    @Transactional
    public AddressDTO save(AddressDTO dto) {
        AddressEntity entity = this.dtoToEntity(dto);
        try {
            entity = addressRepository.save(entity);
            return new AddressDTO(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistsException("Entity already exists");
        }
    }

    @Transactional
    public AddressDTO update(AddressDTO dto, Long id) {
        AddressEntity updated = this.dtoToEntity(dto);
        updated.setId(id);
        return new AddressDTO(addressRepository.save(updated));
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Entity with id {} not found", id);
            throw new EntityNotFoundException("Entity with id ".concat(id.toString()).concat(" not found"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InternalServerErrorException("An internal server error has occurred, please try again later");
        }
    }

    private AddressEntity dtoToEntity(AddressDTO dto) {
        AddressEntity entity = new AddressEntity();
        entity.setUser(userService.findEntityByUuid(dto.getUser()));
        entity.setCep(dto.getCep());
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setStreet(dto.getStreet());
        entity.setNumber(dto.getNumber());

        return entity;
    }
}
