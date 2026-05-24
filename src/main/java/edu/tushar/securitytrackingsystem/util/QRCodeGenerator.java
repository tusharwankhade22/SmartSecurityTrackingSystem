package edu.tushar.securitytrackingsystem.util;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


@Component
public class QRCodeGenerator {
	 private static final String QR_DIR = "C:/qrcodes/";

	    public String generateQRCodeImage(String text) {
	        try {
	            String filePath = QR_DIR + text + ".png";
	            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 250, 250);
	            Path path = FileSystems.getDefault().getPath(filePath);
	            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	            return filePath;
	        } catch (Exception e) {
	            throw new RuntimeException("QR Code generation failed");
	        }
	    }
}
