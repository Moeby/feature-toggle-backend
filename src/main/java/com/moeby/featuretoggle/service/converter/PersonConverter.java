package com.moeby.featuretoggle.service.converter;

import com.moeby.featuretoggle.controller.person.resource.Person;
import com.moeby.featuretoggle.service.common.util.UUIDUtil;

public class PersonConverter {

    public Person from(com.moeby.featuretoggle.service.model.Person dbPerson) {
        if (dbPerson == null) {
            return null;
        }
        return new Person(UUIDUtil.toString(dbPerson.getPersonId()), dbPerson.getEmail());
    }

}
