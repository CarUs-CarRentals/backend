package com.carus.services;

import com.carus.entities.ImageEntity;
import com.carus.repositories.ImageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<ImageEntity> saveAll(List<ImageEntity> images) {
        return imageRepository.saveAll(images);
    }

    public void deleteAllByCarId(Long carId) {
        imageRepository.deleteAllByCarId(carId);
    }
}
