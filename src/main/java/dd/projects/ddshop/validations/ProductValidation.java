package dd.projects.ddshop.validations;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.repositories.ProductRepository;
import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.exceptions.IncorrectInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductValidation {

    private final ProductRepository productRepository;

    @Autowired
    public ProductValidation(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void productValidation(final ProductDTO productDTO){
        checkEmpty(productDTO);
        checkDuplicate(productDTO.getName(),true);
    }

    public void productValidation(final seeProductDTO productDTO, final boolean name){

        checkDuplicate(productDTO.getName(),name);
    }

    private void checkDuplicate(final String name,final boolean initial) {
        if(productRepository.findByName(name)!=null && initial)
            throw new IncorrectInput(Util.getMessage("api.error.duplicate",new Object[]{"Product","name"}));
    }

    private void checkEmpty(final ProductDTO productDTO) {
        if(productDTO.getName()==null || productDTO.getDescription()==null||productDTO.getSubcategoryId()<0||productDTO.getName().isEmpty() || productDTO.getDescription().isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }
}
