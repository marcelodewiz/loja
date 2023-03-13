package br.com.escaioni.productapi.service;

import br.com.escaioni.productapi.converter.DTOConverter;
import br.com.escaioni.productapi.model.Product;
import br.com.escaioni.productapi.repository.CategoryRepository;
import br.com.escaioni.productapi.repository.ProductRepository;
import br.com.escaioni.shoppingclient.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        List<Product> products = productRepository.getProductByCategory(categoryId);

        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return DTOConverter.convert(product);
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null){
            return DTOConverter.convert(product);
        }
        return null;
    }

    public ProductDTO save(ProductDTO productDTO){
        Product product = productRepository.save(Product.convert(productDTO));
        return DTOConverter.convert(product);
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
        return DTOConverter.convert(productRepository.save(product));
    }

    public Page<ProductDTO> getAllPage(Pageable page){
        Page<Product> users = productRepository.findAll(page);
        return users
                .map(DTOConverter::convert);
    }
}
