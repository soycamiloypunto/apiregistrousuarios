package com.example.apiregistrousuarios.repository;

import com.example.apiregistrousuarios.entity.Usser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsserCRUDRepository extends CrudRepository<Usser, Long> {
    List<Usser> findByEmail(String email);
    //Optional<User> findByEmail(String email);


}
