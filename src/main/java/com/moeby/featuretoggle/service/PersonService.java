package com.moeby.featuretoggle.service;

import com.moeby.featuretoggle.service.converter.PersonConverter;
import com.moeby.featuretoggle.service.dao.IPersonDAO;
import com.moeby.featuretoggle.service.model.Person;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class PersonService implements IPersonService {

    private final IPersonDAO personDAO;
    private final PersonConverter personConverter;

    public PersonService(IPersonDAO personDAO, PersonConverter personConverter) {
        this.personDAO = personDAO;
        this.personConverter = personConverter;
    }

    @Override
    public Person get(String id) {
        return null;
    }

    @Override
    public List<Person> get(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return personDAO.findByIds(ids);
    }

    @Override
    public List<com.moeby.featuretoggle.controller.person.resource.Person> getAll() {
        List<Person> people = personDAO.findAll();
        return people.stream()
                .map(personConverter::from)
                .toList();
    }

}
