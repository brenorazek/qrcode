package br.com.razek.qrcode.mapper;

import br.com.razek.qrcode.dto.AvatarDTO;
import br.com.razek.qrcode.dto.PersonDTO;
import br.com.razek.qrcode.entity.Avatar;
import br.com.razek.qrcode.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AvatarMapper {

    AvatarMapper INSTANCE = Mappers.getMapper(AvatarMapper.class);

    Avatar toModel(AvatarDTO avatarDTO);

    AvatarDTO toDTO(Avatar avatar);
}
