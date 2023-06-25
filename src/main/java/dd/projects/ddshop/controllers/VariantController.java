package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.services.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Attr;

import javax.persistence.criteria.CriteriaBuilder;
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

    @GetMapping("/getLowestPrice")
    public ResponseEntity<Float> getLowestPrice() {
        return new ResponseEntity<>(variantService.getLowestPrice(), HttpStatus.OK);
    }
    @GetMapping("/getHighestPrice")
    public ResponseEntity<Float> getHighestPrice() {
        return new ResponseEntity<>(variantService.getHighestPrice(), HttpStatus.OK);
    }

    @GetMapping("/getVariantsBySubcategory/{id}")
    public ResponseEntity<List<VariantDTO>> getVariantsBySubcategory(@PathVariable final int id) {
        return new ResponseEntity<>(variantService.getVariantsBySubcategory(id), HttpStatus.OK);
    }

    @GetMapping("/getUrlBySubcategory/{id}")
    public ResponseEntity<String> getUrlVariantBySubcategory(@PathVariable final int id) {
        return new ResponseEntity<>(variantService.getUrlBySubId(id), HttpStatus.OK);
    }

    @GetMapping("/getColorsByProductId/{id}")
    public ResponseEntity<List<ColorsDTO>> getColorsByProductId(@PathVariable final int id) {
        return new ResponseEntity<>(variantService.getColorsByProductId(id), HttpStatus.OK);
    }
    @GetMapping("/getVariantFirstAtt")
    public ResponseEntity<List<SizesDTO>> getVariantFirstAttribute(@RequestParam(name= "prodId") final int prodid,@RequestParam(name= "attId") final int attid) {
        return new ResponseEntity<>(variantService.getVariantFirstAttribute(prodid,attid), HttpStatus.OK);
    }
    @GetMapping("/getVariantSecondAtt")
    public ResponseEntity<List<SizesDTO>> getVariantSecondAttribute(@RequestParam(name= "prodId") final int prodid,
                                                                    @RequestParam(name= "attId1") final int attid1,
                                                                    @RequestParam(name= "attId2") final int attid2,
                                                                    @RequestParam(name= "valId") final String valId) {
        return new ResponseEntity<>(variantService.getVariantSecondAttribute(prodid,attid1,attid2,valId), HttpStatus.OK);
    }
    @GetMapping("/getSizesByColor/{id}")
    public ResponseEntity<List<SizesDTO>> getSizesByColor(@PathVariable final int id, @RequestParam(name= "color") final int color) {
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

    @PostMapping ("getFilteredVariants/{id}")
    public ResponseEntity<List<VariantDTO>> getFilteredVariants(@RequestBody final FilterDTO filters, @PathVariable final int id){
        System.out.println("here");
        return new ResponseEntity<>(variantService.getFilteredVariants(filters,id),HttpStatus.OK);
    }
    @PostMapping ("getFilteredVariantsAndWord/{id}")
    public ResponseEntity<List<VariantDTO>> getFilteredVariantsByWord(@RequestParam(name= "name") final String name,@RequestBody final FilterDTO filters, @PathVariable final int id){
        System.out.println("here");
        return new ResponseEntity<>(variantService.getFilteredVariantsBySearch(filters,id,name),HttpStatus.OK);
    }

}
