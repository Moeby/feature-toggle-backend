package com.moeby.featuretoggle.controller.person;

import com.moeby.featuretoggle.controller.featuretoggle.resource.FeatureStatus;
import com.moeby.featuretoggle.controller.person.resource.Person;
import com.moeby.featuretoggle.service.IPersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/people")
public class PersonController {

    private final IPersonService personService;

    PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin("*")
    @GetMapping(value = "")
    public ResponseEntity<List<Person>> getAll() {
        final List<Person> people = personService.getAll();

        if (CollectionUtils.isEmpty(people)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(people);
    }


    @CrossOrigin("*")
    @GetMapping(value = "/{id}/features")
    // Comment: Changed from POST to GET to respect HTTP rules, I think the list should be short enough given that toggles shouldn't live for too long to not require the POST approach.
    public ResponseEntity<List<FeatureStatus>> getFeatures(@PathVariable("id") String personId,
                                                              @RequestParam(name = "features", required = false) List<String> technicalNames) {
        return null; // TODO: Implement
    }


}
