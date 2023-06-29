package dd.projects.ddshop.services;

import dd.projects.ddshop.models.Feature;
import dd.projects.ddshop.repositories.FeatureRepository;
import dd.projects.ddshop.utils.ImageStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {
    private final FeatureRepository featureRepository;

    @Autowired
    FeatureService(final FeatureRepository featureRepository){
        this.featureRepository = featureRepository;
    }

    public List<Feature> getAll(){
        return  featureRepository.findAll();
    }

    public void save(List<Feature> features){
        features.forEach((feature -> {
            featureRepository.save(feature);
        }));
    }
    public String getUrl(String url){
        return ImageStorageUtil.hostImage(String.valueOf(url.hashCode()), url);
    }
}
