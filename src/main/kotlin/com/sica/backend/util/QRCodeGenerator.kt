package com.sica.backend.util

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.util.*

object QRCodeGenerator {

    fun generateQRCodeBase64(text: String, width: Int = 300, height: Int = 300): String {
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
        
        val outputStream = ByteArrayOutputStream()
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)
        
        val qrCodeBytes = outputStream.toByteArray()
        return Base64.getEncoder().encodeToString(qrCodeBytes)
    }
}
