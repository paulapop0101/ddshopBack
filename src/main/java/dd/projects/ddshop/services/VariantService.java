package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.mappers.VariantMapper;
import dd.projects.ddshop.models.*;
import dd.projects.ddshop.repositories.*;
import dd.projects.ddshop.utils.ImageStorageUtil;
import dd.projects.ddshop.validations.VariantValidation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class VariantService {


    private final VariantRepository variantRepository;

    private final VariantMapper variantMapper = Mappers.getMapper(VariantMapper.class);

    private final ProductRepository productRepository;

    private final VariantValidation variantValidation;

    private final AttributeValueRepository attributeValueRepository;

    private final AssignedValueRepository assignedValueRepository;




    @Autowired
    public VariantService(final VariantRepository variantRepository, final ProductRepository productRepository, final AssignedValueRepository assignedValueRepository, final AttributeValueRepository attributeValueRepository, final AssignedValueRepository assignedValueRepository1){
        this.variantRepository=variantRepository;
        this.productRepository = productRepository;
        this.variantValidation  = new VariantValidation(assignedValueRepository);
        this.attributeValueRepository = attributeValueRepository;
        this.assignedValueRepository = assignedValueRepository1;
    }

    public VariantDTO addVariant(final VariantCreateDTO variantCreateDTO){
        variantValidation.variantValidation(variantCreateDTO);

        final Variant variant= variantMapper.toModel(variantCreateDTO);

        variant.setProduct(productRepository.getReferenceById(variantCreateDTO.getProduct_id()));
        variant.setAdded_date(Timestamp.valueOf(LocalDateTime.now()));
        System.out.println(variantCreateDTO.getProduct_id());
        System.out.println(productRepository.getReferenceById(variantCreateDTO.getProduct_id()).getId());

        final Variant v= variantRepository.save(variant);
        System.out.println(v.getId()+""+v.getAdded_date());
        return new VariantDTO();
    }

    public List<VariantDTO> getAllVariants() {
        return variantMapper.toDTO(variantRepository.findAll());
    }
    //for printing variants on home page
    public List<VarAssignedValueDTO> getVarAssignedValues(final Variant variant){
        final List<VarAssignedValueDTO> valueDTO = new ArrayList<>();
        final Map<ProductAttribute,List<AssignedValue>> map = new HashMap<>();
        final List<AssignedValue> assignedValues = new ArrayList<>();
//        for(final AssignedValue assignedValue : variant.getAssignedValues()) {
//            map.put(assignedValue.getProductAttribute(),)
//        }
        return valueDTO;
    }
    public List<VarAssignedValueDTO> getAssignedValues(final int product_id){
        final Subcategory subcategory = productRepository.getReferenceById(product_id).getSubcategory();
        final List<VarAssignedValueDTO> values = new ArrayList<>();
        for(final ProductAttribute attribute : subcategory.getProductAttributes()){
            values.add(new VarAssignedValueDTO(attribute.getName(),getValues(attribute)));
        }

        return values;
    }

    private List<idValuesDTO> getValues(final ProductAttribute attribute) {
        final List<idValuesDTO> list = new ArrayList<>();
        for(final AttributeValue value : attribute.getAttributeValues())
            list.add( new idValuesDTO(
                    assignedValueRepository.findByProductAttributeAndAttributeValue(attribute,value).getId(),
                    value.getValue())
            );
        return list;
    }

    public List<VariantDTO> getVariantsBySubcategoryId(final int subcategoryId){
        final Set<DistinctVariantIdsDTO> list = variantRepository.getDistinctVariantsByColor();
        final List<Integer> ids = new ArrayList<>();
        for(final DistinctVariantIdsDTO item : list)
            ids.add(item.getId());
        return variantMapper.toDTO(variantRepository.getVariantsByProductSubcategoryIdAndIdInDistinctIds(subcategoryId,ids));
    }

    public List<ColorsDTO> getColorsByProductId(final int productId){
        final List<ColorsDTO> colors = new ArrayList<>();
        final List<AttributeValue> attributeValues = attributeValueRepository.findColorsByProductId(productId);
        for (final AttributeValue attributeValue : attributeValues)
            colors.add(new ColorsDTO(attributeValue.getId(),attributeValue.getValue()));
        return colors;
    }
    public List<SizesDTO> getSizesByColor(final int productId, final int color) {
        final List<SizesDTO> sizes = variantRepository.findSizesByProductID(productId,color);

        return sizes;
    }
    public void updateVariant(final VariantUpdateDTO variantDTO, final int id){
        final Variant oldvariant = variantMapper.toModel(variantDTO);
        final Variant newvariant = variantRepository.getReferenceById(id);
        newvariant.setPrice(oldvariant.getPrice());
        newvariant.setQuantity(oldvariant.getQuantity());
        newvariant.setAssignedValues(oldvariant.getAssignedValues());
        if(variantDTO.isNewpicture()) {
            String pictureUri = ImageStorageUtil.hostImage(String.valueOf(variantDTO.hashCode()), variantDTO.getPicture());
            newvariant.setUrl(pictureUri);
        }
        System.out.println("here");
        variantRepository.save(newvariant);
    }
    public boolean deleteVariant(final int id) {
        variantExists(id);
        variantRepository.deleteById(id);
        return true;
    }
    public boolean deleteVariants(final List<Integer> ids){
        variantRepository.deleteAllById(ids);
        return true;
    }

    public void variantExists(final int id)  {
        if(!variantRepository.existsById(id)){
            throw new EntityDoesNotExist("Exception: Variant was not found!");
        }
    }

    public VariantDTO getVariant(final int id) {
        return variantMapper.toDTO(variantRepository.getReferenceById(id));
    }

    public float getLowestPrice() {

        return variantRepository.getFirstVariantByPriceASC().getPrice();
    }
    public float getHighestPrice() {
        return 2;
    }

    public List<VariantDTO> getVariantsBySubcategory(final int id){
        return variantMapper.toDTO(variantRepository.getByProduct_SubcategoryId(id));
    }

    public List<VariantDTO> getFilteredVariants(FilterDTO filters, int id) {
        List<Variant> filtered;
        if(filters.getAttributes().size() > 0) {
            filtered = variantRepository.getFilteredByAttributes(id, filters.getLprice(), filters.getHprice(), filters.getAttributes().get(0).getAttributeId(), filters.getAttributes().get(0).getValueIds());
            List<Variant> aux;
            for (int i = 1; i < filters.getAttributes().size(); i++) {
                aux = variantRepository.getFilteredByAttributes(id, filters.getLprice(), filters.getHprice(), filters.getAttributes().get(i).getAttributeId(), filters.getAttributes().get(i).getValueIds());
                filtered.retainAll(aux);
            }
        }
        else {
            filtered = variantRepository.getFilteredByPrice(id, filters.getLprice(), filters.getHprice());
        }
        return variantMapper.toDTO(filtered);
    }

    public List<VariantDTO> getFilteredVariantsBySearch(FilterDTO filters, int id, String word) {
        System.out.println(word);
        List<Variant> filtered;
        if(filters.getAttributes().size() > 0) {
            filtered = variantRepository.getFilteredByAttributesWord(id, filters.getLprice(), filters.getHprice(), filters.getAttributes().get(0).getAttributeId(), filters.getAttributes().get(0).getValueIds(),word);
            List<Variant> aux;
            for (int i = 1; i < filters.getAttributes().size(); i++) {
                aux = variantRepository.getFilteredByAttributesWord(id, filters.getLprice(), filters.getHprice(), filters.getAttributes().get(i).getAttributeId(), filters.getAttributes().get(i).getValueIds(),word);
                filtered.retainAll(aux);
            }
        }
        else {
            filtered = variantRepository.getFilteredByPriceWord(id, filters.getLprice(), filters.getHprice(), word);
        }
        return variantMapper.toDTO(filtered);
    }

    public List<SizesDTO> getVariantFirstAttribute(int prodid, int attid) {
        return variantRepository.getVariantsFirstAttribute(prodid,attid);
    }

    public List<SizesDTO> getVariantSecondAttribute(int prodid, int attid1, int attid2, String valId) {
        return variantRepository.getVariantsSecondAttribute(prodid,attid1,attid2,valId);
    }
    public String getUrlBySubId(int id){
        Variant variant = variantRepository.getFirstByProductSubcategoryId(id);
        if(variant!=null)
            return variant.getUrl();
        return "../../../assets/images/frontpage.jpeg";
    }

}
