package edu.tushar.securitytrackingsystem.util;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Component
public class QRCodeGenerator {

    public byte[] generateQRCodeImage(String text) {
        try {

            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(text, BarcodeFormat.QR_CODE, 250, 250);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(
                    bitMatrix,
                    "PNG",
                    outputStream
            );

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("QR Code generation failed", e);
        }
    }
}