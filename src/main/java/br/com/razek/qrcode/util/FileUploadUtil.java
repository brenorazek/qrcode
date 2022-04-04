package br.com.razek.qrcode.util;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileUploadUtil {

    public static void save(MultipartFile file, String uploadDir, String fileName) {
        Path uploadPath = Paths.get(uploadDir);
        try {
            Files.copy(file.getInputStream(), uploadPath.resolve(Objects.requireNonNull(fileName)), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage() + uploadPath + uploadDir);
        }
    }
}
