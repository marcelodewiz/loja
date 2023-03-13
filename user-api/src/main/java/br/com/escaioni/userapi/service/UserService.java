package br.com.escaioni.userapi.service;

import br.com.escaioni.shoppingclient.dto.UserDTO;
import br.com.escaioni.userapi.converter.DTOConverter;
import br.com.escaioni.userapi.model.User;
import br.com.escaioni.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId){
        User user = userRepository
                .findById(userId).orElseThrow(() -> new RuntimeException());
        return DTOConverter.convert(user);
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return DTOConverter.convert(user);
    }

    public void delete(Long userId){
        User user = userRepository
                .findById(userId).orElseThrow(() -> new RuntimeException());

        userRepository.delete(user);
    }

    public UserDTO findByCpf(String cpf, String key){
        User user = userRepository.findByCpfAndKey(cpf, key);
        if(user != null){
            return DTOConverter.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name){
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editUser(Long userId, UserDTO userDTO){
        User user = userRepository
                .findById(userId).orElseThrow(()-> new RuntimeException());
        if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())) {
            user.setTelefone(userDTO.getTelefone());
        }
        if (userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())) {
            user.setEndereco(userDTO.getEndereco());
        }

        user = userRepository.save(user);
        return DTOConverter.convert(user);
    }

    public Page<UserDTO> getAllPage(Pageable page){
        Page<User> users = userRepository.findAll(page);
        return users.map(DTOConverter::convert);
    }
}
