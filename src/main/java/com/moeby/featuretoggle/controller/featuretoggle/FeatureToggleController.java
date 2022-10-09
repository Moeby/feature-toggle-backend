package com.moeby.featuretoggle.controller.featuretoggle;

import com.moeby.featuretoggle.controller.featuretoggle.resource.Feature;
import com.moeby.featuretoggle.service.IFeatureToggleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/features")
public class FeatureToggleController {

    private final IFeatureToggleService featureToggleService;

    FeatureToggleController(IFeatureToggleService featureToggleService) {
        this.featureToggleService = featureToggleService;
    }

    @CrossOrigin("*")
    @PostMapping(value = "")
    public ResponseEntity<Feature> add(@RequestBody Feature feature) {
        final Feature addedFeature = featureToggleService.add(feature);

        if (addedFeature == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addedFeature);
    }

    @CrossOrigin("*")
    @GetMapping(value = "")
    public ResponseEntity<List<Feature>> getAll() {
        List<Feature> features = featureToggleService.loadAll();
        return ResponseEntity.ok(features);
    }

    @CrossOrigin("*")
    @PostMapping(value = "/{id}/archive")
    public ResponseEntity<Feature> archive(@PathVariable("id") String featureId, @RequestBody(required = false) Feature feature) {
        Feature archivedFeature = featureToggleService.archive(featureId);
        return ResponseEntity.ok(archivedFeature);
    }

}
