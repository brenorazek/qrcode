package br.com.razek.qrcode.controller;

import br.com.razek.qrcode.exceptions.PersonNotFoundException.PersonNotFoundException;
import br.com.razek.qrcode.dto.PersonDTO;
import br.com.razek.qrcode.entity.Person;
import br.com.razek.qrcode.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/perfil")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;


    @PostMapping
    public Person createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @PutMapping
    public Person updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
