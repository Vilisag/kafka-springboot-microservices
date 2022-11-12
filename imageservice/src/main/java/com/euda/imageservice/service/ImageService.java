package com.euda.imageservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.euda.imageservice.exception.ResourceNotFoundException;
import com.euda.imageservice.model.Image;
import com.euda.imageservice.repository.ImageRepository;

public interface ImageService {
    List<Image> getAll();
    Image getOne(String id);
    Image create(Image image);
    Image update(String id, Image image);
    void delete(String id);
}

@Service
class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image getOne(String id) {
        return imageRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Account: %s, not found", id)));
    }

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image update(String id, Image image) {
        Image originImage = this.getOne(id);
        originImage.setTitle(image.getTitle());
        originImage.setUrl(image.getUrl());
        return imageRepository.save(originImage);
    }

    @Override
    public void delete(String id) {
        Image image = this.getOne(id);
        imageRepository.delete(image);
    }
}