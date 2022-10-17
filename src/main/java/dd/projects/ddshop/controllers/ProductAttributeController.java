package dd.projects.ddshop.controllers;


import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
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

//    @PostMapping("/addAttributee")
//    public ResponseEntity<AttributeCreateDTO> addAttributee(@RequestBody final AttributeCreateDTO attributeCreateDTO) {
//        return new ResponseEntity<>(attributeService.addAttributee(attributeCreateDTO), HttpStatus.OK);
//    }

    @PostMapping("/addAttribute")
    public ResponseEntity<Integer> addAttribute(@RequestParam(name= "name") final String name) {
        return new ResponseEntity<>(attributeService.addAttribute(name), HttpStatus.OK);
    }
    @PostMapping("/addSubcategoryToAttributee/{id}")
    public ResponseEntity<SubcategoryDTO> addSubcategoryToAttributee(@RequestBody final SubcategoryDTO subcategoryDTO, @PathVariable final int id) {
        return new ResponseEntity<>(attributeService.addSubcategoryToAttributee(subcategoryDTO,id), HttpStatus.OK);
    }
    @PostMapping("/addSubcategoryToAttribute/{id}")
    public ResponseEntity<Boolean> addSubcategoryToAttributeee(@RequestBody final List<Integer> subcategoryDTO, @PathVariable final int id) {
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
    @PutMapping("/deleteSubcategoryFromAttribute/{id}")
    public boolean deleteSubcategoryFromAttribute(@PathVariable final int id,@RequestBody final SubcategoryDTO subcategoryDTO){
        return attributeService.deleteSubcategoryFromAttribute(id,subcategoryDTO);
    }


}
