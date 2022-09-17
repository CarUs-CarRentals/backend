package com.carus.services;

import com.carus.dto.AddressDTO;
import com.carus.entities.AddressEntity;
import com.carus.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public AddressDTO create(AddressEntity address) {
        return new AddressDTO(addressRepository.save(address));
    }

    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream().map(entity -> new AddressDTO(entity)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AddressDTO findById(Long id) {
        return new AddressDTO(addressRepository.findById(id).orElse(null));
    }

    @Transactional
    public AddressDTO save(AddressDTO dto) {
        AddressEntity entity = addressRepository.save(this.DTOToEntity(dto));
        return new AddressDTO(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    private AddressEntity DTOToEntity(AddressDTO dto) {
        AddressEntity entity = new AddressEntity();
        entity.setCep(dto.getCep());
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setStreet(dto.getStreet());
        entity.setNumber(dto.getNumber());

        return entity;
    }
}
