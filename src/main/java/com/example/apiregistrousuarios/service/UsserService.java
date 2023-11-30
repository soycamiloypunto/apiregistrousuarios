package com.example.apiregistrousuarios.service;


import com.example.apiregistrousuarios.entity.Usser;
import com.example.apiregistrousuarios.repository.UsserRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;


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
        return usserRepository.save(usser);
    }

    public boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return emailPattern.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?!.*\\s).{5,}$");
        return password.matches(String.valueOf(PASSWORD_PATTERN));
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

    public String setToken(Usser usser) {
        // Crear a JWT builder
        JwtBuilder builder = Jwts.builder();
        // Configurar el Token
        builder.setSubject(usser.getName());
        // Configurar vencimiento
        builder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60));
        // Firmar
        builder.signWith(SignatureAlgorithm.HS256, "my-secret-key");
        // Devuelvo el Token
        return builder.compact();

    }

    //pruebas


}

