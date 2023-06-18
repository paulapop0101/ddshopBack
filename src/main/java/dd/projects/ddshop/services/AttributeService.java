package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.IncorrectInput;
import dd.projects.ddshop.mappers.AttributeMapper;
import dd.projects.ddshop.models.*;
import dd.projects.ddshop.repositories.*;
import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.validations.AttributeValidation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AttributeService {


    private final ProductAttributeRepository productAttributeRepository;
    private final AttributeValueRepository attributeValueRepository;
    private final AssignedValueRepository assignedValueRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final AttributeValidation attributeValidation;
    private final AttributeMapper attributeMapper = Mappers.getMapper(AttributeMapper.class);

    @Autowired
    public AttributeService(final ProductAttributeRepository productAttributeRepository, final AttributeValueRepository attributeValueRepository, final AssignedValueRepository assignedValueRepository, final SubcategoryRepository subcategoryRepository){
        this.productAttributeRepository=productAttributeRepository;
        this.attributeValueRepository=attributeValueRepository;
        this.assignedValueRepository = assignedValueRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.attributeValidation = new AttributeValidation(productAttributeRepository);
    }


//    public AttributeCreateDTO addAttributee(final AttributeCreateDTO attributeCreateDTO) {
//        attributeValidation.attributeValidation(attributeCreateDTO);
//
//        final ProductAttribute attribute = attributeMapper.toModel(attributeCreateDTO);
//
//        addValues(attribute,attributeCreateDTO.getValues());
//
//        productAttributeRepository.save(attribute);
//        saveAssignedValues(attribute); // save (attribute - value) combination
//
//        return attributeCreateDTO;
//    }

    private void addValues(final ProductAttribute attribute, final List<String> values) {
        final List<AttributeValue> attributeValues = new ArrayList<>();
        for(final String value : values) {
            attributeValues.add(new AttributeValue(value, attribute));
        }
        attribute.setAttributeValues(attributeValues);
    }


    private void saveAssignedValues(final ProductAttribute attribute) {
        for(final AttributeValue attributeValue : attribute.getAttributeValues())
            assignedValueRepository.save(new AssignedValue(attribute,attributeValue));
    }

    public boolean deleteAttributeValue(final int id){
        attributeValueRepository.deleteById(id);
        return true;

    }
    public String addAttributeValue(final int id, final String value){
        attributeValidation.checkAttributeValue(value,id);

        final ProductAttribute productAttribute = productAttributeRepository.getReferenceById(id);
        final AttributeValue attributeValue=new AttributeValue(value,productAttribute);
        productAttribute.getAttributeValues().add(attributeValue);
        productAttributeRepository.save(productAttribute);

        assignedValueRepository.save(new AssignedValue(productAttribute,attributeValidation.getAttributeValue(productAttribute,value)));

        return value;

    }

    public boolean deleteAttribute(final int id){
        productAttributeRepository.deleteById(id);
        return true;
    }


    public List<AttributeDTO> getAttributes(){

        return productAttributeRepository.findAll()
                .stream()
                .map(attributeMapper::toDTO)
                .collect(toList());
    }

    public String addSubcategoryToAttributee(final int subcategoryId, final int id) {
        final ProductAttribute attribute = productAttributeRepository.getReferenceById(id);
        if(!attribute.getSubcategories().contains(subcategoryRepository.getReferenceById(subcategoryId)))
            attribute.getSubcategories().add(subcategoryRepository.getReferenceById(subcategoryId));
        productAttributeRepository.save(attribute);

        return "ok";
    }
    public boolean addSubcategoryToAttribute(final List<Integer> subcategory_id, final int id) {
        final ProductAttribute attribute = productAttributeRepository.getReferenceById(id);
        for(final int sub_id: subcategory_id)
            attribute.getSubcategories().add(subcategoryRepository.getReferenceById(sub_id));
        productAttributeRepository.save(attribute);

        return true;
    }

    public int addAttribute(final String name) {
        productAttributeRepository.save(new ProductAttribute(name));
        return productAttributeRepository.getProductAttributeByName(name).getId();
    }

    public boolean deleteSubcategoryFromAttribute(final int id, final SubcategoryDTO subcategoryDTO) {
        final ProductAttribute attribute = productAttributeRepository.getReferenceById(id);
        final Subcategory s= subcategoryRepository.getSubcategoryByNameAndCategory_Name(subcategoryDTO.getName(),subcategoryDTO.getCategoryName());
        s.getProductAttributes().remove(attribute);
        attribute.getSubcategories().remove(s);
        productAttributeRepository.save(attribute);
        subcategoryRepository.save(s);
        return true;
    }

    public String editValue(String name, int id) {
        if(name=="")
            throw new IncorrectInput(Util.getMessage("api.error.empty.field", null));

        AttributeValue value = attributeValueRepository.getReferenceByValue(name);

        if(value!=null)
            throw new EntityAlreadyExists(Util.getMessage("api.error.duplicate", new Object[]{"Value","name"}));

        value = attributeValueRepository.getReferenceById(id);
        value.setValue(name);
        attributeValueRepository.save(value);
        return "ok";
    }

    public String editAttribute(String name, int id) {
        if(name=="")
            throw new IncorrectInput(Util.getMessage("api.error.empty.field", null));

        ProductAttribute productAttribute = productAttributeRepository.getProductAttributeByName(name);

        if(productAttribute!=null)
            throw new EntityAlreadyExists(Util.getMessage("api.error.duplicate", new Object[]{"Attribute","name"}));

        productAttribute = productAttributeRepository.getReferenceById(id);
        productAttribute.setName(name);
        productAttributeRepository.save(productAttribute);
        return "ok";
    }
}
