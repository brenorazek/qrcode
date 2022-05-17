package br.com.razek.qrcode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO{

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String department;

    private List<PhoneDTO> phones;

    private AvatarDTO avatar;

}
