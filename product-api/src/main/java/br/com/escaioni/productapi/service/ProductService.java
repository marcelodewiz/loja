package br.com.escaioni.productapi.service;

import br.com.escaioni.productapi.dto.ProductDTO;
import br.com.escaioni.productapi.model.Product;
import br.com.escaioni.productapi.repository.CategoryRepository;
import br.com.escaioni.productapi.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        List<Product> products = productRepository.getProductByCategory(categoryId);

        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null){
            return ProductDTO.convert(product);
        }
        return null;
    }

    public ProductDTO save(ProductDTO productDTO){
        Product product = productRepository.save(Product.convert(productDTO));
        return ProductDTO.convert(product);
    }

    public void delete(long productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            productRepository.delete(productOptional.get());
        }
    }

    public ProductDTO editProduct(long id, ProductDTO dto){
        Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));

        if(dto.getNome() != null || !dto.getNome().isEmpty()){
            product.setNome(dto.getNome());
        }
        if(dto.getPreco() != null){
            product.setPreco(dto.getPreco());
        }
        return ProductDTO.convert(productRepository.save(product));
    }

    public Page<ProductDTO> getAllPage(Pageable page){
        Page<Product> users = productRepository.findAll(page);
        return users
                .map(ProductDTO::convert);
    }
}
