package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    private final ProductService productService;


    public ProductController(final ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<seeProductDTO> addProduct(@RequestBody final ProductDTO productDTO){
        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.OK);
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<seeProductDTO>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
    }
    @GetMapping("/getProductsBySubcategory/{id}")
    public ResponseEntity<List<seeProductDTO>> getProductsBySubcategory(@PathVariable final int id){
        return new ResponseEntity<>(productService.getProductsBySubcategory(id),HttpStatus.OK);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public boolean deleteProduct(@PathVariable final int id){
       return productService.deleteProduct(id);
    }
    @PutMapping("/deleteProducts")
    public boolean deleteProducts(@RequestBody final List<Integer> id){
        return productService.deleteProducts(id);
    }

    @PutMapping("/updateProduct")
    public boolean updateProduct(@RequestBody final seeProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }
}
