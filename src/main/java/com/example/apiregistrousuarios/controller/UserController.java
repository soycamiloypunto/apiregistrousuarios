package com.example.apiregistrousuarios.controller;

import com.example.apiregistrousuarios.entity.Usuario;
import com.example.apiregistrousuarios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Usuario> getUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getUser(@PathVariable("id") long id){
        return userService.getUser(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario usuario){
        return userService.save(usuario);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario update(@RequestBody Usuario usuario){
        return userService.update(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") long id){
        return userService.deleteUser(id);
    }

    //Customs
    @GetMapping("/user/{email}")
    public List<Usuario> findByEmail(@PathVariable("email") String email){
        return userService.findByEmail(email);
    }

}

