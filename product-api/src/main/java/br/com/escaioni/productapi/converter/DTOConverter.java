package br.com.escaioni.productapi.converter;

import br.com.escaioni.productapi.model.Category;
import br.com.escaioni.productapi.model.Product;
import br.com.escaioni.shoppingclient.dto.CategoryDTO;
import br.com.escaioni.shoppingclient.dto.ProductDTO;

public class DTOConverter {

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setDescricao(product.getDescricao());
        if(product.getCategory() != null){
            productDTO.setCategory(DTOConverter.convert(product.getCategory()));
        }

        return productDTO;
    }

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }
}
