package br.com.razek.qrcode.services;

import br.com.razek.qrcode.dto.AvatarDTO;
import br.com.razek.qrcode.dto.PersonDTO;
import br.com.razek.qrcode.entity.Avatar;
import br.com.razek.qrcode.entity.Person;
import br.com.razek.qrcode.exceptions.AvatarNotFoundException;
import br.com.razek.qrcode.mapper.AvatarMapper;
import br.com.razek.qrcode.repository.AvatarRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AvatarService {

    private AvatarRepository avatarRepository;

    private final AvatarMapper avatarMapper = AvatarMapper.INSTANCE;

    //@Value("${avatar.base.path}")
    //public String uploadDir;

    public byte[] findById(Long id) throws AvatarNotFoundException, IOException {
        String uploadDir = "etc/avatar/";
        Avatar avatar = verifyIfExists(id);
        String fileName = avatar.getName();
        File serverFile = new File(uploadDir + fileName);
        return Files.readAllBytes(serverFile.toPath());
    }

    public List<AvatarDTO> listAll(){
        List<Avatar> allAvatar = avatarRepository.findAll();
        return allAvatar.stream().map(avatarMapper::toDTO).collect(Collectors.toList());
    }

    private Avatar verifyIfExists(Long id) throws AvatarNotFoundException {
        System.out.println(id);
        return avatarRepository.findById(id)
                .orElseThrow(() -> new AvatarNotFoundException(id));
    }

}
