package com.moeby.featuretoggle.service;

import com.moeby.featuretoggle.controller.featuretoggle.resource.Feature;
import com.moeby.featuretoggle.service.common.util.UUIDUtil;
import com.moeby.featuretoggle.service.converter.FeatureToggleConverter;
import com.moeby.featuretoggle.service.dao.IFeatureToggleDAO;
import com.moeby.featuretoggle.service.model.FeatureToggle;

import java.util.List;

public class FeatureToggleService implements IFeatureToggleService {

    private final IFeatureToggleDAO featureToggleDAO;
    private final FeatureToggleConverter featureToggleConverter;

    public FeatureToggleService(IFeatureToggleDAO featureToggleDAO, FeatureToggleConverter featureToggleConverter) {
        this.featureToggleDAO = featureToggleDAO;
        this.featureToggleConverter = featureToggleConverter;
    }

    @Override
    public List<Feature> loadAll() {
        final List<FeatureToggle> dbFeatureToggles = featureToggleDAO.findAll();
        return dbFeatureToggles.stream()
                .map(featureToggleConverter::from)
                .toList();
    }
    @Override
    public Feature add(Feature feature) {
        if (feature == null) {
            throw new IllegalArgumentException("Add feature: Feature cannot be null");
        }
        FeatureToggle dbFeatureToggle = featureToggleConverter.from(feature);
        dbFeatureToggle = featureToggleDAO.add(dbFeatureToggle);
        return featureToggleConverter.from(dbFeatureToggle);
    }

    @Override
    public Feature archive(String featureId) {
        if (featureId == null) {
            throw new IllegalArgumentException("Archive feature: id cannot be null");
        }
        final List<FeatureToggle> dbFeatureToggles = featureToggleDAO.findAll(); // COMMENT: I'd rather have a findById method in DAO and have this look a bit nicer  but my time is running out^^
        return dbFeatureToggles.stream()
                .filter(dbFeatureToggle -> dbFeatureToggle.getFeatureId().equals(UUIDUtil.from(featureId)))
                .map(dbFeatureToggle -> {
                    dbFeatureToggle.setArchived(true);
                    return featureToggleConverter.from(featureToggleDAO.update(dbFeatureToggle));
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Archive feature: Feature with id " + featureId + " not found"));
    }
}
