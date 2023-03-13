package br.com.escaioni.productapi.controller;

import br.com.escaioni.productapi.service.ProductService;
import br.com.escaioni.shoppingclient.dto.ProductDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts(){
        return productService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId){
        return productService.getProductByCategoryId(categoryId);
    }

    @GetMapping("/{productIdentifier}")
    public ProductDTO findById(@PathVariable String productIdentifier){
        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @PatchMapping("/{id}")
    public ProductDTO editProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.editProduct(id, productDTO);
    }

    @GetMapping("/{id}/id")
    public ProductDTO findById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @GetMapping("/pageable")
    public Page<ProductDTO> getProductsPage(Pageable pageable){
        return productService.getAllPage(pageable);
    }
}
