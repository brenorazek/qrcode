package br.com.razek.qrcode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AvatarNotFoundException extends Exception {

    public AvatarNotFoundException(Long id) {
        super("Avatar not found with id " + id);
    }
}
