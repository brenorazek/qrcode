package br.com.razek.qrcode.services;

import br.com.razek.qrcode.dto.UserDTO;
import br.com.razek.qrcode.entity.User;
import br.com.razek.qrcode.exceptions.UserNotFoundException.UserNotFoundException;
import br.com.razek.qrcode.mapper.UserMapper;
import br.com.razek.qrcode.repository.UserRepository;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public User createUser(UserDTO userDTO){
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDTO.setSecret(Hashing.sha256().hashString(userDTO.getEmail(), StandardCharsets.UTF_8).toString());
        User userToSave = userMapper.toModel(userDTO);
        return userRepository.save(userToSave);
    }

    public UserDTO findById(Long id) throws UserNotFoundException {
        User user = verifyIfExists(id);
        return userMapper.toDTO(user);
    }

    public List<UserDTO> listAll(){
        List<User> allPeople = userRepository.findAll();
        return allPeople.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    private User verifyIfExists(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateById(Long id, UserDTO userDTO) throws UserNotFoundException {
        verifyIfExists(id);
        User userToUpdate = userMapper.toModel(userDTO);
        return userRepository.save(userToUpdate);
    }

    public void delete(Long id) throws UserNotFoundException {
        verifyIfExists(id);
        userRepository.deleteById(id);
    }

}
