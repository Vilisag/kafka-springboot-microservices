package com.euda.imageservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.euda.imageservice.model.Image;
import com.euda.imageservice.service.ImageService;

@RestController
@RequestMapping("/api/v1")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/images")
    public List<Image> index() {
        return imageService.getAll();
    }

    @GetMapping("/images/{id}")
    public Image show(@PathVariable(value = "id") String id) {
        return imageService.getOne(id);
    }

    @PostMapping("/images")
    public Image store(@Valid @RequestBody Image image) {
        return imageService.create(image);
    }

    @PutMapping("/images/{id}")
    public Image update(@PathVariable(value = "id") String id, @Valid @RequestBody Image image) {
        return imageService.update(id, image);
    }

    @DeleteMapping("/images/{id}")
    public Map<String, String> delete(@PathVariable(value = "id") String id) {
        imageService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("code", "200");
        response.put("message", "Image was deleted successful.");
        return response;
    }
}
