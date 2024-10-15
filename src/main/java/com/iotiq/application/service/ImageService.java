package com.iotiq.application.service;

import com.iotiq.application.exception.FileTypeNotAllowedException;
import com.iotiq.application.messages.image.ImageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${image.allowed-types}")
    private List<String> allowedContentTypes;

    // Save image in a local directory
    public ImageDto upload(String uploadDirectory, MultipartFile imageFile) throws IOException {

        checkFileType(imageFile.getContentType());

        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));
        if (originalFileName.contains("..")) {
            throw new IOException("Invalid file path");
        }

        String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

        Path uploadPath = Path.of(uploadDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return new ImageDto(uniqueFileName);
    }

    private void checkFileType(final String contentType) {
        if (!allowedContentTypes.contains(contentType)) {
            throw new FileTypeNotAllowedException(contentType,
                    StringUtils.collectionToDelimitedString(allowedContentTypes, ", "));
        }
    }

}