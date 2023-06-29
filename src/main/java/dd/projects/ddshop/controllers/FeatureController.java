package dd.projects.ddshop.controllers;

import dd.projects.ddshop.models.Feature;
import dd.projects.ddshop.services.FeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FeatureController {

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping("/getFeatures")
    public ResponseEntity<List<Feature>> getFeatures(){
        return new ResponseEntity<>(featureService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/getUrl")
    public ResponseEntity<String> getUrl(@RequestBody final String url){
        return new ResponseEntity<>(featureService.getUrl(url), HttpStatus.OK);
    }
    @PostMapping("/saveFeatures")
    public void saveFeatures(@RequestBody final List<Feature> features){
        System.out.println(features);
        featureService.save(features);
    }

}
