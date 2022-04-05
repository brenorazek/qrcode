package br.com.razek.qrcode.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
public class FileUploadUtil {

    public static void save(MultipartFile file, String fileName, String uploadDir) {
        String newFileName = getExtension(file, fileName);
        System.out.println(uploadDir);
        System.out.println(fileName);
        Path uploadPath = Paths.get(uploadDir);
        try {
            Files.copy(file.getInputStream(), uploadPath.resolve(Objects.requireNonNull(newFileName)), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage() + uploadPath + uploadDir);
        }
    }

    private static String getExtension(MultipartFile file, String fileName){
        String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf("."));
        fileName = fileName + extension;
        return fileName;
    }
}
