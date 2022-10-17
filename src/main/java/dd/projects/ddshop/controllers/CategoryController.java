package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(final CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }
    @GetMapping("/getSubcategories")
    public ResponseEntity<List<SubcategoryDTO>> getSubcategories(){
        return new ResponseEntity<>(categoryService.getSubcategories(), HttpStatus.OK);
    }
    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategory(@RequestParam(name= "name") final String name){
        System.out.println("here add cat");
        return new ResponseEntity<>(categoryService.addCategory(name),HttpStatus.OK);
    }
    @PostMapping("/addSubcategory/{id}")
    public ResponseEntity<String> addSubcategory(@RequestParam(name= "name") final String name, @PathVariable final int id){
        return new ResponseEntity<>(categoryService.addSubcategory(name,id),HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public boolean deleteCategory(@PathVariable final int id){
        return categoryService.deleteCategory(id);
    }
    @DeleteMapping("/deleteSubcategory/{id}")
    public boolean deleteSubcategory(@PathVariable final int id){
        return categoryService.deleteSubcategory(id);
    }

}
