package com.sica.backend.controller

import com.sica.backend.dto.FileUploadResponse
import com.sica.backend.util.FileStorageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/upload")
class FileUploadController(
    private val fileStorageService: FileStorageService
) {

    @PostMapping
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<FileUploadResponse> {
        return try {
            val fileUrl = fileStorageService.storeFile(file)
            val response = FileUploadResponse(
                success = true,
                url = fileUrl,
                fileUrl = fileUrl
            )
            ResponseEntity.ok(response)
        } catch (ex: Exception) {
            val response = FileUploadResponse(
                success = false,
                error = ex.message
            )
            ResponseEntity.badRequest().body(response)
        }
    }
}
