package br.com.razek.qrcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String department;

    private List<PhoneDTO> phones;

}
