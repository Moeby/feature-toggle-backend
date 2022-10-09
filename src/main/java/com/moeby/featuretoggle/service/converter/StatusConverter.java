package com.moeby.featuretoggle.service.converter;

import com.moeby.featuretoggle.controller.featuretoggle.resource.Status;
import com.moeby.featuretoggle.service.common.util.StatusUtil;
import com.moeby.featuretoggle.service.model.FeatureToggle;

public class StatusConverter {

    public Status from(FeatureToggle featureToggle) {
        if (featureToggle == null) {
            return null;
        }
        return new Status(StatusUtil.isOverallActivate(featureToggle), featureToggle.isSelected(), StatusUtil.isExpired(featureToggle), featureToggle.isArchived());
    }



}
