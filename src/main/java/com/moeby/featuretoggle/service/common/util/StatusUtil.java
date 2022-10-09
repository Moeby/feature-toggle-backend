package com.moeby.featuretoggle.service.common.util;

import com.moeby.featuretoggle.service.model.FeatureToggle;

import java.time.LocalDateTime;

public class StatusUtil {

    public static boolean isExpired(FeatureToggle featureToggle) {
        return featureToggle.getExpirationDate() != null && featureToggle.getExpirationDate().isBefore(LocalDateTime.now());
    }

    public static boolean isOverallActivate(FeatureToggle featureToggle) {
        return featureToggle.isSelected() && !featureToggle.isArchived() && !isExpired(featureToggle);
    }
}
