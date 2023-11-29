package com.example.apiregistrousuarios.repository;

import com.example.apiregistrousuarios.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UserRepository {
    @Autowired
    private UserCRUDRepository userCRUDRepository;

    //Get ALl
    public List<Usuario> getAll(){
        return (List<Usuario>) userCRUDRepository.findAll();
    }

    //Get por Id
    public Optional<Usuario> getUser(Long id){
        return userCRUDRepository.findById(id);
    }

    //SAVE
    public Usuario save(Usuario Usuario){
        return userCRUDRepository.save(Usuario);
    }

    //DELETE
    public void delete(Usuario Usuario){
        userCRUDRepository.delete(Usuario);
    }

    //CUSTOMS
    //Mostrar Por Email
    public List<Usuario> findByEmail(String email){
        return userCRUDRepository.findByEmail(email);
    }
}
