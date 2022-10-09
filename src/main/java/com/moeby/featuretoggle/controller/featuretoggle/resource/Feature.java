package com.moeby.featuretoggle.controller.featuretoggle.resource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record Feature(String id, String displayName, String technicalName, String description,
                      LocalDateTime expirationDate, Status status, List<String> peopleIds) {

    public Feature {
        Objects.requireNonNull(technicalName);
        Objects.requireNonNull(status);
    }
}
