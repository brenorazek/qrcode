package br.com.razek.qrcode.controller;

import br.com.razek.qrcode.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    @Value("${avatar.base.path}")
    public String uploadDir;

    @PostMapping
    public ResponseEntity<HttpStatus> UploadImage (@RequestParam("file") MultipartFile file, @RequestParam("name") String fileName){
        FileUploadUtil.save(file, fileName, uploadDir);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
