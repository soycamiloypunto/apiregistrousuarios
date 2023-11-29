package com.example.apiregistrousuarios.controller;

import com.example.apiregistrousuarios.entity.Phone;
import com.example.apiregistrousuarios.entity.Phone;
import com.example.apiregistrousuarios.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping("/all")
    public List<Phone> getUsers(){
        return phoneService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Phone> getUser(@PathVariable("id") int id){
        return phoneService.getUser(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Phone save(@RequestBody Phone phone){
        return phoneService.save(phone);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Phone update(@RequestBody Phone phone){
        return phoneService.update(phone);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return phoneService.deleteUser(id);
    }


}
