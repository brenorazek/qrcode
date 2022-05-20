package br.com.razek.qrcode.services;

import br.com.razek.qrcode.dto.UserDTO;
import br.com.razek.qrcode.entity.User;
import br.com.razek.qrcode.exceptions.DataIntegrityViolationException;
import br.com.razek.qrcode.exceptions.UserNotFoundException.UserNotFoundByEmailException;
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

    public User findByEmail(String email) throws UserNotFoundByEmailException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundByEmailException(email));
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

    public String updatePassword(String email, String currentPassword, String newPassword) throws UserNotFoundByEmailException, DataIntegrityViolationException {
        User user = findByEmail(email);
        if (!encoder.matches(currentPassword, user.getPassword())) {
            throw new  DataIntegrityViolationException("Senha atual incorreta!");
        }else if(encoder.matches(newPassword, user.getPassword())) {
            throw new  DataIntegrityViolationException("Senha atual não pode ser igual a anterior!");
        }else{
            String cryptNewPassword = encoder.encode(newPassword);
            user.setPassword(cryptNewPassword);
            userRepository.save(user);
        }

        return null;
    }

    public void delete(Long id) throws UserNotFoundException {
        verifyIfExists(id);
        userRepository.deleteById(id);
    }

}
