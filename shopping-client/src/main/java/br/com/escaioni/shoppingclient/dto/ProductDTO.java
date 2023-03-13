package br.com.escaioni.shoppingclient.dto;

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
    private CategoryDTO category;


}
