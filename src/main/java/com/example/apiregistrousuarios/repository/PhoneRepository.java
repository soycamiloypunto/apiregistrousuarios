package com.example.apiregistrousuarios.repository;

import com.example.apiregistrousuarios.entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class PhoneRepository {
    @Autowired
    private PhoneCRUDRepository phoneCRUDRepository;

    //Get ALl
    public List<Phone> getAll(){
        return (List<Phone>) phoneCRUDRepository.findAll();
    }

    //Get por Id
    public Optional<Phone> getUser(int id){
        return phoneCRUDRepository.findById(id);
    }

    //SAVE
    public Phone save(Phone phone){
        return phoneCRUDRepository.save(phone);
    }

    //DELETE
    public void delete(Phone phone){
        phoneCRUDRepository.delete(phone);
    }

    //CUSTOMS

}
