package br.com.razek.qrcode.controller;

import br.com.razek.qrcode.dto.UserChangePasswordDTO;
import br.com.razek.qrcode.dto.UserDTO;
import br.com.razek.qrcode.entity.User;
import br.com.razek.qrcode.exceptions.DataIntegrityViolationException;
import br.com.razek.qrcode.exceptions.PersonNotFoundException.PersonNotFoundException;
import br.com.razek.qrcode.exceptions.UserNotFoundException.UserNotFoundByEmailException;
import br.com.razek.qrcode.exceptions.UserNotFoundException.UserNotFoundException;
import br.com.razek.qrcode.security.JWTUtil;
import br.com.razek.qrcode.services.UserService;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    private JWTUtil jwtUtil;


    @PutMapping("/changePassword")
    public String changePassword (HttpServletRequest request, @RequestBody UserChangePasswordDTO userChangePasswordDTO) throws UserNotFoundByEmailException, DataIntegrityViolationException {

        String token = request.getHeader("Authorization");
        String userEmail = jwtUtil.getUsername(token);

        return userService.updatePassword(userEmail, userChangePasswordDTO.getCurrentPassword(), userChangePasswordDTO.getNewPassword());
    }

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
