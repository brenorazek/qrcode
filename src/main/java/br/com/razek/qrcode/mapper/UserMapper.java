package br.com.razek.qrcode.mapper;

import br.com.razek.qrcode.dto.UserDTO;
import br.com.razek.qrcode.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
