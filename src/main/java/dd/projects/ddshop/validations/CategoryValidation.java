package dd.projects.ddshop.validations;

import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.IncorrectInput;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Subcategory;
import dd.projects.ddshop.repositories.CategoryRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;

import java.util.List;

public class CategoryValidation {
    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    public CategoryValidation(final CategoryRepository categoryRepository, final SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }


    public void categoryValidation(final String name){
        checkEmpty(name);
        checkExists(name);
    }
    public void subcategoryValidation(final String name,Category category){
        checkEmpty(name);
        checkSubcategoryExists(name,category.getSubcategories());
    }
    private void checkEmpty(final String name) {
        if(name.isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }

    private void checkExists(final String name) {
        for(final Category c : categoryRepository.findAll())
            if(c.getName().equals(name))
                throw new EntityAlreadyExists(Util.getMessage("api.error.duplicate", new Object[]{"Category","name"}));
    }
    private void checkSubcategoryExists(final String name, List<Subcategory> subcategoryList) {
            for(final Subcategory subcategory : subcategoryList)
                if(subcategory.getName().equals(name))
                    throw new EntityAlreadyExists(Util.getMessage("api.error.duplicate", new Object[]{"Subcategory","name"}));
    }

}
