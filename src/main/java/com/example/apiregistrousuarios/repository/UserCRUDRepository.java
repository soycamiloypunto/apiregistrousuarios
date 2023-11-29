package com.example.apiregistrousuarios.repository;

import com.example.apiregistrousuarios.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCRUDRepository extends CrudRepository<Usuario, Long> {
    List<Usuario> findByEmail(String email);

}
