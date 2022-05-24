package br.com.razek.qrcode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataIntegrityViolationException extends Exception {

    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
