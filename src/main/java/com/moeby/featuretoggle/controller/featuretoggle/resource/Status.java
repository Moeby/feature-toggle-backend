package com.moeby.featuretoggle.controller.featuretoggle.resource;

public record Status(boolean overallActive, boolean selected, boolean expired, boolean archived) {
}
