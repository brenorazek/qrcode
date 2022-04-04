package br.com.razek.qrcode.mapper;

import br.com.razek.qrcode.dto.PersonDTO;
import br.com.razek.qrcode.entity.Person;
import br.com.razek.qrcode.entity.Phone;
import br.com.razek.qrcode.enums.PhoneType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);

}
