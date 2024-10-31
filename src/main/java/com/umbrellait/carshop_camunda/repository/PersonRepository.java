package com.umbrellait.carshop_camunda.repository;

import com.umbrellait.carshop_camunda.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Person JPA repository
 *
 * @author artem.tereshchenko
 *
 */
public interface PersonRepository extends JpaRepository<Person, String> {
}