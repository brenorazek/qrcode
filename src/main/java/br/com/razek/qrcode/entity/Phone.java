package br.com.razek.qrcode.entity;

import br.com.razek.qrcode.dto.PhoneDTO;
import br.com.razek.qrcode.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type;

    @Column(nullable = false)
    private String number;

    public Phone(PhoneDTO phoneDTO){
        this.id = getId();
        this.type = getType();
        this.number = getNumber();
    }
}
