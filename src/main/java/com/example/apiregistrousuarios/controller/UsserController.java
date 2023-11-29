package com.example.apiregistrousuarios.controller;

import com.example.apiregistrousuarios.entity.Usser;
import com.example.apiregistrousuarios.service.UsserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UsserController {
    @Autowired
    private UsserService usserService;

    @GetMapping("/all")
    public List<Usser> getUsers(){
        return usserService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Usser> getUser(@PathVariable("id") long id){
        return usserService.getUser(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Usser save(@RequestBody Usser usser){
        return usserService.save(usser);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Usser update(@RequestBody Usser usser){
        return usserService.update(usser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") long id){
        return usserService.deleteUser(id);
    }

    //Customs
    @GetMapping("/user/{email}")
    public List<Usser> findByEmail(@PathVariable("email") String email){
        return usserService.findByEmail(email);
    }

}

