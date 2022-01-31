package br.com.razek.qrcode.services;

import java.util.Map;

public interface QrCodeGeneratorService {
    boolean generateQRCode(String qrCodeContent, String filePath, Map hashMap, int width, int height);
}
