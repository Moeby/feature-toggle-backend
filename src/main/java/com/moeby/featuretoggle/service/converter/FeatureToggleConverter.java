package com.moeby.featuretoggle.service.converter;

import com.moeby.featuretoggle.controller.featuretoggle.resource.Feature;
import com.moeby.featuretoggle.controller.featuretoggle.resource.Status;
import com.moeby.featuretoggle.service.IPersonService;
import com.moeby.featuretoggle.service.common.util.UUIDUtil;
import com.moeby.featuretoggle.service.model.FeatureToggle;

import java.util.List;

public class FeatureToggleConverter {

    private final IPersonService personService;
    private final StatusConverter statusConverter;

    public FeatureToggleConverter(IPersonService personService, StatusConverter statusConverter) {
        this.personService = personService;
        this.statusConverter = statusConverter;
    }

    public FeatureToggle from(Feature feature) {
        if (feature == null) {
            return null;
        }
        final FeatureToggle featureToggle = new FeatureToggle();
        featureToggle.setDisplayName(feature.displayName());
        featureToggle.setTechnicalName(feature.technicalName());
        featureToggle.setDescription(feature.description());
        featureToggle.setExpirationDate(feature.expirationDate());
        featureToggle.setArchived(feature.status().archived());
        featureToggle.setSelected(feature.status().selected());
        featureToggle.setPeople(personService.get(feature.peopleIds()));
        return featureToggle;
    }

    public Feature from(FeatureToggle featureToggle) {
        if (featureToggle == null) {
            return null;
        }
        final Status status = statusConverter.from(featureToggle);
        final List<String> personIds = featureToggle.getPeople().stream()
                .map(person -> UUIDUtil.toString(person.getPersonId()))
                .toList();
        return new Feature(UUIDUtil.toString(featureToggle.getFeatureId()),
                featureToggle.getDisplayName(),
                featureToggle.getTechnicalName(),
                featureToggle.getDescription(),
                featureToggle.getExpirationDate(),
                status,
                personIds
        );
    }
}
