package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.services.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class VariantController {
    private final VariantService variantService;

    @Autowired
    public VariantController(final VariantService variantService){
        this.variantService = variantService;
    }

    @GetMapping("/getAllVariants")
    public ResponseEntity<List<VariantDTO>> getAllVariants() {
        return new ResponseEntity<>(variantService.getAllVariants(), HttpStatus.OK);
    }
    @GetMapping("/getVariant/{id}")
    public ResponseEntity<VariantDTO> getVariant(@PathVariable final int id) {
        return new ResponseEntity<>(variantService.getVariant(id), HttpStatus.OK);
    }

    @GetMapping("/getAssignedValues/{id}")
    public ResponseEntity<List<VarAssignedValueDTO>> getAssignedValues(@PathVariable final int id) {
        return new ResponseEntity<>(variantService.getAssignedValues(id), HttpStatus.OK);
    }

    @GetMapping("/getVariantsBySubcategory/{id}")
    public ResponseEntity<List<VariantDTO>> getVariantsBySubcategory(@PathVariable final int id) {
        return new ResponseEntity<>(variantService.getVariantsBySubcategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/getColorsByProductId/{id}")
    public ResponseEntity<List<ColorsDTO>> getColorsByProductId(@PathVariable final int id) {
        return new ResponseEntity<>(variantService.getColorsByProductId(id), HttpStatus.OK);
    }
    @GetMapping("/getSizesByColor/{id}")
    public ResponseEntity<List<SizesDTO>> getSizesByColor(@PathVariable final int id, @RequestParam(name= "color") final String color) {
        return new ResponseEntity<>(variantService.getSizesByColor(id,color), HttpStatus.OK);
    }

    @PostMapping("/addVariant")
    public ResponseEntity<VariantDTO> addVariant(@RequestBody final VariantCreateDTO variantCreateDTO){
        return new ResponseEntity<>(variantService.addVariant(variantCreateDTO),HttpStatus.OK);
    }

    @PutMapping("/updateVariant/{id}")
    public boolean updateVariant(@RequestBody final VariantUpdateDTO variant,@PathVariable final int id)  {
        variantService.updateVariant(variant,id);
        return true;
    }

    @PutMapping("/deleteVariants")
    public boolean deleteVariants(@RequestBody final List<Integer> ids){
        return variantService.deleteVariants(ids);
    }
    @DeleteMapping("/deleteVariant/{id}")
    public boolean deleteVariant(@PathVariable final int id)  {
        return variantService.deleteVariant(id);
    }
}
