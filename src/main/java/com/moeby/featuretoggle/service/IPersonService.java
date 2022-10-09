package com.moeby.featuretoggle.service;

import com.moeby.featuretoggle.service.model.Person;

import java.util.List;

public interface IPersonService {

    Person get(String id);

    List<Person> get(List<String> ids);

    List<com.moeby.featuretoggle.controller.person.resource.Person> getAll();

}
