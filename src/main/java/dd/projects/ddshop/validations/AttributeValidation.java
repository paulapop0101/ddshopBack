package dd.projects.ddshop.validations;

import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.IncorrectInput;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.repositories.ProductAttributeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttributeValidation {
    private final ProductAttributeRepository productAttributeRepository;

    public AttributeValidation(final ProductAttributeRepository productAttributeRepository) {
        this.productAttributeRepository = productAttributeRepository;
    }

    public void attributeValidation(final AttributeCreateDTO attributeCreateDTO){
        checkEmpty(attributeCreateDTO);
        attributeExists(attributeCreateDTO);
        checkDuplicates(attributeCreateDTO.getValues());
    }
    private void checkEmpty(final AttributeCreateDTO attributeCreateDTO) {
        if(attributeCreateDTO.getName().isEmpty()||attributeCreateDTO.getValues().isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }

    private void checkDuplicates(final List<String> values) {
        final Set<String> set = new HashSet<>(values);
        if(set.size()!=values.size())
            throw new EntityAlreadyExists(Util.getMessage("api.error.duplicates", null));
    }

    private void attributeExists(final AttributeCreateDTO attributeCreateDTO) {
        for(final ProductAttribute attribute : productAttributeRepository.findAll())
            if(attribute.getName().equals(attributeCreateDTO.getName()))
                throw new EntityAlreadyExists(Util.getMessage("api.error.duplicate", new Object[]{"Attribute","name"}));
    }
    public void checkAttributeValue(final String value, final int id){
        if(value.isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
        if(getAttributeValue(productAttributeRepository.getReferenceById(id),value)!=null)
            throw  new EntityAlreadyExists(Util.getMessage("api.error.duplicate", new Object[]{"Value","name"}));
    }
    public AttributeValue getAttributeValue(final ProductAttribute productAttribute, final String value){
        for(final AttributeValue attributeValue1 : productAttribute.getAttributeValues())
            if(attributeValue1.getValue().equals(value))
                return attributeValue1;

        return null;
    }
}
