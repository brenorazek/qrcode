package br.com.razek.qrcode.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;

}
