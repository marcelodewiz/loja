package br.com.escaioni.shoppingapi.service;

import br.com.escaioni.shoppingclient.dto.ProductDTO;
import br.com.escaioni.shoppingclient.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private String productApiURL = "http://localhost:8001";

    public ProductDTO getProductByIdentifier(String productIdentifier){
        try{
            WebClient webClient = WebClient.builder().baseUrl(productApiURL).build();

            Mono<ProductDTO> product = webClient.get().uri("/product/" + productIdentifier).retrieve().bodyToMono(ProductDTO.class);

            return product.block();
        }catch (Exception exception){
            throw new ProductNotFoundException();
        }
    }
}
