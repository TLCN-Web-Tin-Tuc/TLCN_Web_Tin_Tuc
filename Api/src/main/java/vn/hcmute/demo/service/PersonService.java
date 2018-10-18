package vn.hcmute.demo.service;

import vn.hcmute.demo.entity.Person;

public interface PersonService {
    Person findPersonById(Long id);
	Person createPerson(Person person, long uid);
}
