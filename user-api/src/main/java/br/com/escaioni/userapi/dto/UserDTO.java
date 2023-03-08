package br.com.escaioni.userapi.dto;

import br.com.escaioni.userapi.model.User;
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

    public static UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setKey(user.getKey());
        userDTO.setDataCadastro(user.getDataCadastro());

        return userDTO;
    }
}
