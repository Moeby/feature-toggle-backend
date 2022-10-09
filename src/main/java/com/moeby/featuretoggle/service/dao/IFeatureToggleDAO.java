package com.moeby.featuretoggle.service.dao;

import com.moeby.featuretoggle.service.model.FeatureToggle;

import java.util.List;

public interface IFeatureToggleDAO {
    FeatureToggle add(FeatureToggle featureToggle);

    FeatureToggle update(FeatureToggle featureToggle);

    List<FeatureToggle> findAll();

    List<FeatureToggle> findByTechnicalNames(List<String> technicalNames);
}
