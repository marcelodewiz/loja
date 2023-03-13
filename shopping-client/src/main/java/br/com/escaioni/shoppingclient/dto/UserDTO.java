package br.com.escaioni.shoppingclient.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Nome é obrigatorio")
    private String nome;
    @NotBlank(message = "CPF é obrigatorio")
    private String cpf;
    private String endereco;
    @NotBlank(message = "Email é obrigatorio")
    private String email;
    private String telefone;
    private String key;
    private LocalDateTime dataCadastro;

}
