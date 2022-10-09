package com.moeby.featuretoggle.service.dao;

import com.moeby.featuretoggle.service.model.Person;

import java.util.List;

public interface IPersonDAO {

    List<Person> findByIds(List<String> ids);

    List<Person> findAll();
}
