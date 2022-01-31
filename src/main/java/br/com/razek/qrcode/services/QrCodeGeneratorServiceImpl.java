package br.com.razek.qrcode.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class QrCodeGeneratorServiceImpl implements QrCodeGeneratorService{

    @Override
    public boolean generateQRCode(String qrCodeContent, String filePath,Map hashMap, int width, int height) {
       try {
           QRCodeWriter qrCodeWriter = new QRCodeWriter();
           BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, width, height);
           Path path = Paths.get(filePath);
           MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
           return true;
       } catch (WriterException | IOException e){
           System.out.println(e);
       }
         return false;
    }
}
