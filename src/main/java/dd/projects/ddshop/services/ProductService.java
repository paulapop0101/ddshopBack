package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.mappers.ProductMapper;
import dd.projects.ddshop.models.Product;
import dd.projects.ddshop.repositories.ProductRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import dd.projects.ddshop.validations.ProductValidation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private final ProductValidation productValidation;

    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository, final SubcategoryRepository subcategoryRepository, final SubcategoryRepository subcategoryRepository1){
        this.productRepository=productRepository;
        productValidation = new ProductValidation(productRepository);
        this.subcategoryRepository = subcategoryRepository1;
    }

    public seeProductDTO addProduct(final ProductDTO productDTO){
        productValidation.productValidation(productDTO);
        final Product product = productMapper.toModel(productDTO);
        product.setSubcategory(subcategoryRepository.getReferenceById(productDTO.getSubcategoryId()));
        product.setVariants(new ArrayList<>());
        productRepository.save(product);
        return productMapper.toDTO(productRepository.findByName(product.getName()));
    }
    public List<seeProductDTO> getProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(toList());
    }
    public List<seeProductDTO> getProductsBySubcategory(final int id){
        return productRepository.findBySubcategoryId(id)
                .stream()
                .map(productMapper::toDTO)
                .collect(toList());
    }

    public boolean deleteProduct(final int id){
        productRepository.deleteById(id);
        return true;
    }
    public boolean deleteProducts(final List<Integer> id){
        productRepository.deleteAllById(id);
        return true;
    }

    public boolean updateProduct(final seeProductDTO productDTO){

        final Product product = productRepository.getReferenceById(productDTO.getId());
        productValidation.productValidation(productDTO,!product.getName().equals(productDTO.getName()));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSubcategory(subcategoryRepository.getReferenceById(productDTO.getSubcategory().getId()));
        productRepository.save(product);
        return true;
    }

}
