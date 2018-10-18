package vn.hcmute.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.demo.entity.Person;
import vn.hcmute.demo.exception.UserNotFoundException;

import vn.hcmute.demo.repository.PersonRepository;
import vn.hcmute.demo.repository.UserRepository;
import vn.hcmute.demo.service.PersonService;

import java.util.Date;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public Person findPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if(!person.isPresent())
            throw new UserNotFoundException("Not Found Person");
        return person.get();
    }
	
	@Override
    public Person createPerson(Person person,long uid) {
        person.setDateCreated(new Date());
        person.setUser(userRepository.findById(uid).get());
        person.setUserCreated(person.getUser().getEmail());
        return personRepository.save(person);
    }
}
