package br.com.razek.qrcode.exceptions.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundByEmailException extends Exception{

    public UserNotFoundByEmailException(String email) {
        super("User not found with email " + email);
    }
}
