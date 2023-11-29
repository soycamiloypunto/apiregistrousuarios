package com.example.apiregistrousuarios.service;


import com.example.apiregistrousuarios.entity.Usuario;
import com.example.apiregistrousuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    public List<Usuario> getAll(){
        return UserRepository.getAll();
    }

    public Optional<Usuario> getUser(long id){
        return UserRepository.getUser(id);
    }

    public Usuario save(Usuario Usuario){
        if(Usuario.getId()==null){
            return UserRepository.save(Usuario);
        }else{
            Optional<Usuario> e=UserRepository.getUser(Usuario.getId());
            if(e.isEmpty()){
                return UserRepository.save(Usuario);
            }else{
                return Usuario;
            }
        }
    }

    public Usuario update(Usuario Usuario){
        if(Usuario.getId()!=null){
            return UserRepository.save(Usuario);
        }else{
            Optional<Usuario> e=UserRepository.getUser(Usuario.getId());
            if(!e.isEmpty()){
                if(Usuario.getName()!=null){
                    e.get().setName(Usuario.getName());
                }
                if(Usuario.getEmail()!=null){
                    e.get().setEmail(Usuario.getEmail());
                }
                if(Usuario.getPassword()!=null){
                    e.get().setPassword(Usuario.getPassword());
                }

                UserRepository.save(e.get());
                return e.get();
            }else{
                return Usuario;
            }
        }
    }

    public boolean deleteUser(long id){
        Boolean aBoolean=getUser(id).map(User -> {
            UserRepository.delete(User);
            return true;
        }).orElse(aBoolean=false);

        return aBoolean;
    }

    //Customs

    public List<Usuario> findByEmail(String email){
        return UserRepository.findByEmail(email);
    }


}

