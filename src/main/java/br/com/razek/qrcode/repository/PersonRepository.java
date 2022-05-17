package br.com.razek.qrcode.repository;

import br.com.razek.qrcode.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public List<Person> findAllByOrderByIdAsc();

    //Person findByLastnameAndFirstname(String lastname, String firstname);

    //Person findByFirstname(String firstname);

    //Person findByName(String name);

    Person findByFirstnameAndLastname(String firstname, String lastname);
}
