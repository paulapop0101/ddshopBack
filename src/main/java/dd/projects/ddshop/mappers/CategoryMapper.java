package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    default SubcategoryDTO toDTO(Subcategory subcategory){
        return new SubcategoryDTO(subcategory.getId(),subcategory.getName(),subcategory.getCategory().getName());
    }
    List<SubcategoryDTO> toDTO(List<Subcategory> subcategoryList);

}
