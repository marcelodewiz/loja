package br.com.escaioni.userapi.controller;

import br.com.escaioni.userapi.dto.UserDTO;
import br.com.escaioni.userapi.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(Pageable pageable){
        return userService.getAllPage(pageable);
    }
    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf(
            @RequestParam(name = "key") String key,
            @PathVariable String cpf){
        return userService.findByCpf(cpf,key);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO){
        return userService.save(userDTO);
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(
            @RequestParam(name = "nome", required = true) String nome
    ){
        return userService.queryByName(nome);
    }

    @PatchMapping("/{id}")
    public UserDTO editUser(@PathVariable Long id,
                            @RequestBody UserDTO userDTO){
        return userService.editUser(id, userDTO);
    }
}
