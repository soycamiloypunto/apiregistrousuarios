package com.example.apiregistrousuarios.service;


import com.example.apiregistrousuarios.entity.Phone;
import com.example.apiregistrousuarios.entity.Usser;
import com.example.apiregistrousuarios.messages.CustomMessageResponse;
import com.example.apiregistrousuarios.repository.UsserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class UsserService {
    @Autowired
    private UsserRepository usserRepository;

    public List<Usser> getAll(){
        return usserRepository.getAll();
    }

    public Optional<Usser> getUser(int id){
        return usserRepository.getUser(id);
    }

    public Usser save(Usser usser) {
        //enlisto los usuarios con ese correo
            if(usser.getId()==null){
                usser.setId(usserRepository.save(usser).getId());
            }else{
                for(Phone phone: usser.getPhones()) {
                    phone.setUsser(usser);
                }
            }
            return usserRepository.save(usser);
    }

    public Usser update(Usser usser){
        if(usser.getId()!=null){
            return usserRepository.save(usser);
        }else{
            Optional<Usser> e= usserRepository.getUser(usser.getId());
            if(!e.isEmpty()){
                if(usser.getName()!=null){
                    e.get().setName(usser.getName());
                }
                if(usser.getEmail()!=null){
                    e.get().setEmail(usser.getEmail());
                }
                if(usser.getPassword()!=null){
                    e.get().setPassword(usser.getPassword());
                }
                if(usser.getModified()!=null){
                    e.get().setModified(usser.getModified());
                }
                if(usser.getLastLogin()!=null){
                    e.get().setLastLogin(usser.getLastLogin());
                }
                if(usser.getPhones()!=null){
                    e.get().setPhones(usser.getPhones());
                }

                usserRepository.save(e.get());
                return e.get();
            }else{
                return usser;
            }
        }
    }

    public boolean deleteUser(int id){
        Boolean aBoolean=getUser(id).map(User -> {
            usserRepository.delete(User);
            return true;
        }).orElse(aBoolean=false);

        return aBoolean;
    }

    //Customs

    public List<Usser> findByEmail(String email){
        return usserRepository.findByEmail(email);
    }



    //pruebas


}

