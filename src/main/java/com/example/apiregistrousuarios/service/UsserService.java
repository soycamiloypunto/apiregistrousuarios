package com.example.apiregistrousuarios.service;


import com.example.apiregistrousuarios.entity.Usser;
import com.example.apiregistrousuarios.repository.UsserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsserService {
    @Autowired
    private UsserRepository UsserRepository;

    public List<Usser> getAll(){
        return UsserRepository.getAll();
    }

    public Optional<Usser> getUser(long id){
        return UsserRepository.getUser(id);
    }

    public Usser save(Usser usser){
        if(usser.getId()==null){
            return UsserRepository.save(usser);
        }else{
            Optional<Usser> e= UsserRepository.getUser(usser.getId());
            if(e.isEmpty()){
                return UsserRepository.save(usser);
            }else{
                return usser;
            }
        }
    }

    public Usser update(Usser usser){
        if(usser.getId()!=null){
            return UsserRepository.save(usser);
        }else{
            Optional<Usser> e= UsserRepository.getUser(usser.getId());
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

                UsserRepository.save(e.get());
                return e.get();
            }else{
                return usser;
            }
        }
    }

    public boolean deleteUser(long id){
        Boolean aBoolean=getUser(id).map(User -> {
            UsserRepository.delete(User);
            return true;
        }).orElse(aBoolean=false);

        return aBoolean;
    }

    //Customs

    public List<Usser> findByEmail(String email){
        return UsserRepository.findByEmail(email);
    }


}

