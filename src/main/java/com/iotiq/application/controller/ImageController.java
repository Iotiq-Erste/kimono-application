package com.iotiq.application.controller;

import com.iotiq.application.messages.image.ImageDto;
import com.iotiq.application.messages.image.ImageResponse;
import com.iotiq.application.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
public class ImageController {

    @Value("${imageDirectory}")
    private String imageDirectory;

    private final ImageService imageService;

    @PostMapping
    public ImageResponse uploadImage(@RequestParam("file") MultipartFile image) throws IOException {
        ImageDto createdImageDto = imageService.saveImageToStorage(imageDirectory, image);
        return ImageResponse.from(createdImageDto);
    }
}
