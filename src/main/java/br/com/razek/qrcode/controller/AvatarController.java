package br.com.razek.qrcode.controller;

import br.com.razek.qrcode.dto.AvatarDTO;
import br.com.razek.qrcode.exceptions.AvatarNotFoundException;
import br.com.razek.qrcode.services.AvatarService;
import br.com.razek.qrcode.util.FileUploadUtil;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
@RequestMapping(value = "/upload")
@AllArgsConstructor
public class AvatarController {

    private AvatarService avatarService;

    //@Value("${avatar.base.path}")
    //public String uploadDir;

    @PostMapping
    public ResponseEntity<HttpStatus> UploadImage (@RequestParam("file") MultipartFile file, @RequestParam("name") String fileName){
        String uploadDir = "C:/qrcode/";
        if(fileName != null){
            FileUploadUtil.save(file, fileName, uploadDir);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] GetImage(@PathVariable Long id) throws IOException, AvatarNotFoundException {
        return avatarService.findById(id);
    }

    @GetMapping
    public List<AvatarDTO> listAll(){
        return avatarService.listAll();
    }

    @PutMapping
    public ResponseEntity<HttpStatus> UpdateImage (@RequestParam("file") MultipartFile file, @RequestParam("name") String avatarName, @RequestParam("avatarId") Long avatarId) throws AvatarNotFoundException {
        if(file != null) {
            return avatarService.updateById(file, avatarName, avatarId);
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
