package br.com.razek.qrcode.dto;

import br.com.razek.qrcode.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO{

    private Long id;

    private String name;

    private String email;

    private String password;

    private List<Phone> phones;

}
