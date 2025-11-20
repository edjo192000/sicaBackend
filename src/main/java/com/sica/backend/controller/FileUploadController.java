package com.sica.backend.controller;

import com.sica.backend.dto.FileUploadResponse;
import com.sica.backend.util.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = fileStorageService.storeFile(file);
            FileUploadResponse response = new FileUploadResponse(
                    true,
                    fileUrl,
                    fileUrl,
                    null
            );
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            FileUploadResponse response = new FileUploadResponse(
                    false,
                    null,
                    null,
                    ex.getMessage()
            );
            return ResponseEntity.badRequest().body(response);
        }
    }
}
