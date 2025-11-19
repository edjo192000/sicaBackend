package com.sica.backend.util

import com.sica.backend.exception.BadRequestException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

@Service
class FileStorageService {

    @Value("\${file.upload-dir}")
    private lateinit var uploadDir: String

    @Value("\${file.max-size}")
    private var maxSize: Long = 5242880

    @Value("\${file.allowed-extensions}")
    private lateinit var allowedExtensions: String

    private val fileStorageLocation: Path by lazy {
        Paths.get(uploadDir).toAbsolutePath().normalize().also {
            try {
                Files.createDirectories(it)
            } catch (ex: Exception) {
                throw RuntimeException("Could not create upload directory", ex)
            }
        }
    }

    fun storeFile(file: MultipartFile): String {
        // Validar tamaño
        if (file.size > maxSize) {
            throw BadRequestException("El archivo excede el tamaño máximo permitido de 5MB")
        }

        // Validar extensión
        val originalFilename = file.originalFilename ?: throw BadRequestException("Nombre de archivo inválido")
        val extension = originalFilename.substringAfterLast(".", "")
        val allowedExtsList = allowedExtensions.split(",").map { it.trim().lowercase() }
        
        if (extension.lowercase() !in allowedExtsList) {
            throw BadRequestException("Tipo de archivo no permitido. Permitidos: $allowedExtensions")
        }

        // Generar nombre único
        val fileName = "${UUID.randomUUID()}.${extension}"

        try {
            val targetLocation = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            return "/uploads/$fileName"
        } catch (ex: IOException) {
            throw RuntimeException("Could not store file $fileName", ex)
        }
    }

    fun deleteFile(fileUrl: String): Boolean {
        return try {
            val fileName = fileUrl.substringAfterLast("/")
            val filePath = fileStorageLocation.resolve(fileName)
            Files.deleteIfExists(filePath)
        } catch (ex: Exception) {
            false
        }
    }
}
