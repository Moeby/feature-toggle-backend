package com.moeby.featuretoggle.service.common.util;

import java.util.List;
import java.util.UUID;

public class UUIDUtil {

    public static List<UUID> from(List<String> id) {
        return id.stream()
                .map(UUIDUtil::from)
                .toList();
    }

    public static UUID from(String id) {
        return UUID.fromString(id);
    }

    public static String toString(UUID id) {
        return String.valueOf(id);
    }
}
