package br.com.escaioni.userapi.repository;

import br.com.escaioni.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpfAndKey(String cpf, String key);
    List<User> queryByNomeLike(String name);
}
