package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.models.Picture;
import dd.projects.ddshop.models.Product;
import dd.projects.ddshop.models.Subcategory;
import dd.projects.ddshop.utils.ImageStorageUtil;
import org.mapstruct.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ProductMapper {


//    @Mappings({
//            @Mapping(target = "pictures", expression = "java(getURLs(productDTO))")
//    })
    Product toModel(ProductDTO productDTO);
//    default List<Picture> getURLs(ProductDTO productDTO) {
//
//        List<Picture> pictureList = new ArrayList<>();
//        for(String picture : productDTO.getPictures())
//        {
//            String pictureUri = ImageStorageUtil.hostImage(String.valueOf(productDTO.hashCode()), picture);
//            pictureList.add(new Picture(pictureUri));
//        }
//
//        return pictureList;
//    }

    seeProductDTO toDTO(Product product);

    default SubcategoryDTO toDTO(Subcategory subcategory){
        return new SubcategoryDTO(subcategory.getId(),subcategory.getName(),subcategory.getCategory().getName());
    }


}
