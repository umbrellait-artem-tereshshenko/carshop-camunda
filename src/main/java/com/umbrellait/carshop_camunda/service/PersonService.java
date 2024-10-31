package com.umbrellait.carshop_camunda.service;

import com.umbrellait.carshop_camunda.exception.PersonNotFoundException;
import com.umbrellait.carshop_camunda.model.Person;
import com.umbrellait.carshop_camunda.model.Role;
import com.umbrellait.carshop_camunda.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Person service class incapsulates business logic for CRUD person operations
 *
 * @author artem.tereshchenko
 *
 */

@Service
@AllArgsConstructor
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return personRepository.findById(username).orElseThrow(
                () -> new PersonNotFoundException(String.format("Person with name %s is not found", username)));
    }

    @Transactional(readOnly = true)
    public Optional<Person> findAnyPersonWithRole(Role role) {

        return personRepository.findAll().stream()
                .filter(person -> person.getRoles().contains(role)).findAny();
    }
}


