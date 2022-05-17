package br.com.razek.qrcode.services;

import br.com.razek.qrcode.exceptions.PersonNotFoundException.PersonNotFoundException;
import br.com.razek.qrcode.dto.PersonDTO;
import br.com.razek.qrcode.entity.Person;
import br.com.razek.qrcode.mapper.PersonMapper;
import br.com.razek.qrcode.repository.PersonRepository;
import com.google.common.hash.Hashing;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    //@Value("${qr.code.directory}")
    //private String qrCodeDirectory;

    @Autowired
    private QrCodeGeneratorService qrCodeGeneratorService;

    public Person createPerson(PersonDTO personDTO){
        //personDTO.setAvatar(Hashing.sha256().hashString(personDTO.getName(), StandardCharsets.UTF_8).toString().substring(0, 10));
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        String qrCodeName = Hashing.sha256().hashString(savedPerson.getFirstname(), StandardCharsets.UTF_8).toString().substring(0, 16);
        GenerateQrCode(qrCodeName);
        return savedPerson;
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public PersonDTO findByName(String firstname, String lastname) throws PersonNotFoundException {
        Person person = personRepository.findByFirstnameAndLastname(firstname, lastname);
        return personMapper.toDTO(person);
    }

    public List<PersonDTO> listAll(){
        List<Person> allPeople = personRepository.findAllByOrderByIdAsc();
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    /*
    private Person verifyIfExistsByName(String firstname) {


     */

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public Person updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        return personRepository.save(personToUpdate);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public void GenerateQrCode(String name) {
        String filePath = "C:\\QRCODEIMAGES\\" + name + ".png";
        String qrCodeContent = "https://qrcode.razek.com.br/qrcode/" + name;
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        int width = 400;
        int height = 400;
        qrCodeGeneratorService.generateQRCode(qrCodeContent, filePath, hashMap, width, height);
    }

}
