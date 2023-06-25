package dd.projects.ddshop.controllers;


import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.dtos.newAttributeDTO;
import dd.projects.ddshop.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductAttributeController {

    private final AttributeService attributeService;

    @Autowired
    public ProductAttributeController(final AttributeService attributeService){
        this.attributeService = attributeService;
    }

    @PostMapping("/addAttribute")
    public ResponseEntity<Integer> addAttribute(@RequestBody final newAttributeDTO attribute) {
        return new ResponseEntity<>(attributeService.addAttribute(attribute), HttpStatus.OK);
    }
    @PostMapping("/addSubcategoryToAttribute/{id}")
    public ResponseEntity<String> addSubcategoryToAttributee(@RequestParam(name= "subcategoryId") final int subcategoryId, @PathVariable final int id) {
        return new ResponseEntity<>(attributeService.addSubcategoryToAttributee(subcategoryId,id), HttpStatus.OK);
    }
    @PostMapping("/addSubcategoriesToAttribute/{id}")
    public ResponseEntity<Boolean> addSubcategoryToAttribute(@RequestBody final List<Integer> subcategoryDTO, @PathVariable final int id) {
        return new ResponseEntity<>(attributeService.addSubcategoryToAttribute(subcategoryDTO,id), HttpStatus.OK);
    }
    @PostMapping("/addAttributeValue/{id}")
    public ResponseEntity<String> addAttributeValue(@RequestParam(name= "value") final String value, @PathVariable final int id) {
        return new ResponseEntity<>(attributeService.addAttributeValue(id,value), HttpStatus.OK);
    }
    @GetMapping("/getString")
    public ResponseEntity<String> getString() {
        Object ob = "hei";
        return new ResponseEntity<>("mystring", HttpStatus.OK);
    }
    @DeleteMapping("/deleteAttributeValue/{id}")
    public boolean deleteAttributeValue(@PathVariable final int id){
       return  attributeService.deleteAttributeValue(id);
    }
    @DeleteMapping("/deleteAttribute/{id}")
    public boolean deleteAttribute(@PathVariable final int id){
        return attributeService.deleteAttribute(id);
    }
    @GetMapping("/getAttributes")
    public ResponseEntity<List<AttributeDTO>> getAttributes(){
        return new ResponseEntity<>(attributeService.getAttributes(), HttpStatus.OK);
    }
    @GetMapping("/getAttributes/{id}")
    public ResponseEntity<List<AttributeDTO>> getAttributesbySubcategory(@PathVariable final int id){
        return new ResponseEntity<>(attributeService.getAttributesbySub(id), HttpStatus.OK);
    }
    @PutMapping("/deleteSubcategoryFromAttribute/{id}")
    public boolean deleteSubcategoryFromAttribute(@PathVariable final int id,@RequestBody final SubcategoryDTO subcategoryDTO){
        return attributeService.deleteSubcategoryFromAttribute(id,subcategoryDTO);
    }
    @PutMapping("/editValueName/{id}")
    public ResponseEntity<String> editValue(@RequestParam(name= "name") final String name, @PathVariable final int id){
        return new ResponseEntity<>(attributeService.editValue(name,id),HttpStatus.OK);
    }
    @PutMapping("/editAttributeName/{id}")
    public ResponseEntity<String> editAttribute(@RequestBody final newAttributeDTO attribute, @PathVariable final int id){
        return new ResponseEntity<>(attributeService.editAttribute(attribute,id),HttpStatus.OK);
    }

    @GetMapping("getPDPAttributes/{id}")
    public ResponseEntity<List<AttributeDTO>> getPDPAttributes(@PathVariable final int id){
        return new ResponseEntity<>(attributeService.getPDPAttributes(id),HttpStatus.OK);
    }
}
