package com.ehcache.service;

import com.ehcache.entity.Person;


public interface PersonService {
    Person save(Person person);

    void remove(Long id);

    Person findOne(Person person);
}
