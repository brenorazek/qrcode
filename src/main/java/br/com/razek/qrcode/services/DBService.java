package br.com.razek.qrcode.services;

import br.com.razek.qrcode.entity.Avatar;
import br.com.razek.qrcode.entity.Person;
import br.com.razek.qrcode.entity.User;
import br.com.razek.qrcode.repository.PersonRepository;
import br.com.razek.qrcode.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class DBService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instaceDB(){
        //Person person1 = new Person(null, "Breno Veronezi", "breno.veronezi@razek.com.br", "TI", new ArrayList<>(1), new Avatar());

        User user1 = new User(null, "Breno Donato Veronezi", "breno.veronezi@razek.com.br", encoder.encode("12345678"), Hashing.sha256().hashString("breno.veronezi@razek.com.br", StandardCharsets.UTF_8).toString());

        //personRepository.save(person1);
        userRepository.save(user1);
    }
}
