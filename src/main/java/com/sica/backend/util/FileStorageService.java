package com.sica.backend.util;

import com.sica.backend.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.max-size}")
    private Long maxSize = 5242880L;

    @Value("${file.allowed-extensions}")
    private String allowedExtensions;

    private Path fileStorageLocation;

    private Path getFileStorageLocation() {
        if (fileStorageLocation == null) {
            fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
            try {
                Files.createDirectories(fileStorageLocation);
            } catch (Exception ex) {
                throw new RuntimeException("Could not create upload directory", ex);
            }
        }
        return fileStorageLocation;
    }

    public String storeFile(MultipartFile file) {
        // Validar tamaño
        if (file.getSize() > maxSize) {
            throw new BadRequestException("El archivo excede el tamaño máximo permitido de 5MB");
        }

        // Validar extensión
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new BadRequestException("Nombre de archivo inválido");
        }

        String extension = "";
        int lastDotIndex = originalFilename.lastIndexOf(".");
        if (lastDotIndex > 0) {
            extension = originalFilename.substring(lastDotIndex + 1);
        }

        List<String> allowedExtsList = Arrays.stream(allowedExtensions.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        if (!allowedExtsList.contains(extension.toLowerCase())) {
            throw new BadRequestException("Tipo de archivo no permitido. Permitidos: " + allowedExtensions);
        }

        // Generar nombre único
        String fileName = UUID.randomUUID().toString() + "." + extension;

        try {
            Path targetLocation = getFileStorageLocation().resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName, ex);
        }
    }

    public boolean deleteFile(String fileUrl) {
        try {
            int lastSlashIndex = fileUrl.lastIndexOf("/");
            String fileName = lastSlashIndex >= 0 ? fileUrl.substring(lastSlashIndex + 1) : fileUrl;
            Path filePath = getFileStorageLocation().resolve(fileName);
            return Files.deleteIfExists(filePath);
        } catch (Exception ex) {
            return false;
        }
    }
}
