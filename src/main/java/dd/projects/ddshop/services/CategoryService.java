package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.mappers.CategoryMapper;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.models.Subcategory;
import dd.projects.ddshop.repositories.CategoryRepository;
import dd.projects.ddshop.repositories.ProductAttributeRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import dd.projects.ddshop.validations.CategoryValidation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    private final CategoryValidation categoryValidation;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository, final SubcategoryRepository subcategoryRepository, final ProductAttributeRepository productAttributeRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
        this.categoryValidation = new CategoryValidation(categoryRepository, subcategoryRepository);
    }

    public String addCategory(final String name){
        categoryValidation.categoryValidation(name);
        final Category category = new Category(name);
        categoryRepository.save(category);
        return name;

    }

    public String addSubcategory(final String name, final int id){
        categoryValidation.subcategoryValidation(name,categoryRepository.getReferenceById(id));
        final Category category = categoryRepository.getReferenceById(id);
        final Subcategory subcategory = new Subcategory(name,category);
        subcategoryRepository.save(subcategory);

        return name;
    }

    public boolean deleteCategory(final int id){
        List<Subcategory> subcategories = categoryRepository.getReferenceById(id).getSubcategories();
        for(Subcategory sub : subcategories){
            for(ProductAttribute productAttribute : sub.getProductAttributes())
                productAttribute.getSubcategories().remove(sub);
        }
        categoryRepository.deleteById(id);
        return true;
    }
    public List<CategoryDTO> getCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(toList());
    }

    public boolean deleteSubcategory(final int id){
        final Subcategory subcategory = subcategoryRepository.getReferenceById(id);

        for( final ProductAttribute attribute : subcategory.getProductAttributes()) {
            attribute.getSubcategories().remove(subcategory);
        }
        subcategory.setProductAttributes(new ArrayList<>());
        subcategoryRepository.deleteById(subcategory.getId());
        return true;
    }
    public List<SubcategoryDTO> getSubcategories(){

        return categoryMapper.toDTO(subcategoryRepository.findAll());
    }

}
