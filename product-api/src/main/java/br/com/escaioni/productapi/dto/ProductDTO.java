package br.com.escaioni.productapi.dto;

import br.com.escaioni.productapi.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    @NotNull(message = "Preço nao deve ser nulo")
    private Float preco;

    @NotBlank(message = "Descrição é obrigatorio")
    private String descricao;

    @NotBlank(message = "Identificação é obrigatorio")
    private String productIdentifier;

    @NotNull(message = "Categoria nao deve ser nulo")
    private CategoryDTO categoryDTO;

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setDescricao(product.getDescricao());
        if(product.getCategory() != null){
            productDTO.setCategoryDTO(CategoryDTO.convert(product.getCategory()));
        }

        return productDTO;
    }
}
