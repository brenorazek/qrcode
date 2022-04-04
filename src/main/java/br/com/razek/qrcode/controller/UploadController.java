package br.com.razek.qrcode.controller;

import br.com.razek.qrcode.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping(value = "/upload")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UploadController {

    @PostMapping
    public ResponseEntity<HttpStatus> UploadImage (@RequestParam("file") MultipartFile file, @RequestParam("name") String fileName){
        String uploadDir = "uploads/";
        String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf("."));
        fileName = fileName + extension;
        FileUploadUtil.save(file, uploadDir, fileName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
