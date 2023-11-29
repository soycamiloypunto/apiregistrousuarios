package com.example.apiregistrousuarios.repository;

import com.example.apiregistrousuarios.entity.Usser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UsserRepository {
    @Autowired
    private UsserCRUDRepository usserCRUDRepository;

    //Get ALl
    public List<Usser> getAll(){
        return (List<Usser>) usserCRUDRepository.findAll();
    }

    //Get por Id
    public Optional<Usser> getUser(int id){
        return usserCRUDRepository.findById(id);
    }

    //SAVE
    public Usser save(Usser usser){
        return usserCRUDRepository.save(usser);
    }

    //DELETE
    public void delete(Usser usser){
        usserCRUDRepository.delete(usser);
    }

    //CUSTOMS
    //Mostrar Por Email
    public List<Usser> findByEmail(String email){
        return usserCRUDRepository.findByEmail(email);
    }
}
