package com.moeby.featuretoggle.service;

import com.moeby.featuretoggle.controller.featuretoggle.resource.Feature;

import java.util.List;

public interface IFeatureToggleService {

    List<Feature> loadAll();

    Feature add(Feature feature);

    Feature archive(String featureId);
}
