package br.com.razek.qrcode.controller;

import br.com.razek.qrcode.dto.UserDTO;
import br.com.razek.qrcode.entity.User;
import br.com.razek.qrcode.exceptions.PersonNotFoundException.PersonNotFoundException;
import br.com.razek.qrcode.exceptions.UserNotFoundException.UserNotFoundException;
import br.com.razek.qrcode.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody @Valid UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping(value = "/{id}")
    public UserDTO findById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDTO> listAll(){
        return userService.listAll();
    }

    @PutMapping(value = "/{id}")
    public User updateById(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) throws UserNotFoundException {
        return userService.updateById(id, userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) throws UserNotFoundException {
        userService.delete(id);
    }
}
