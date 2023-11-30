package com.example.apiregistrousuarios.controller;

import com.example.apiregistrousuarios.entity.Phone;
import com.example.apiregistrousuarios.entity.Usser;
import com.example.apiregistrousuarios.messages.CustomErrorResponse;
import com.example.apiregistrousuarios.messages.CustomMessageResponse;
import com.example.apiregistrousuarios.service.UsserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

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
    public Optional<Usser> getUser(@PathVariable("id") int id){
        return usserService.getUser(id);
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public <T> ResponseEntity<T> save(@RequestBody Usser usser) {
        // Valido si el correo ya existe
        List<Usser> existingUssers = usserService.findByEmail(usser.getEmail());
        if (existingUssers.size() > 0) {
            return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), "El correo ya está registrado"));
        }

        // Valido el formato de EMail
        boolean isValidEmail = usserService.isValidEmail(usser.getEmail());
        if (!isValidEmail) {
            return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), "El correo electrónico ingresado no es válido: EJ mail@mail.com"));
        }

        // Validar contraseña
        boolean isValidPassword = usserService.isValidPassword(usser.getPassword());
        if (!isValidPassword) {
            return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), "La contraseña ingresada no es válida"));
        }


        //Agrego el Id de Usser en Phones
        for(Phone phone: usser.getPhones()) {
            phone.setUsser(usser);
        }

        // Agregar los campos nuevos
        usser.setCreated(LocalDateTime.now());
        usser.setModified(LocalDateTime.now());
        usser.setLastLogin(LocalDateTime.now());

        // Generar el token JWT
        usser.setToken(new UsserService().setToken(usser));

        //Generar el tokjen
        //usser.setToken(UUID.randomUUID().toString());
        usser.setActive(true);

        Usser savedUsser = usserService.save(usser);

        // creo el mensaje de información y lo modifico
        CustomMessageResponse messageResponse  = new CustomMessageResponse();
        messageResponse.setId(usser.getId());
        messageResponse.setCreated(usser.getCreated());
        messageResponse.setModified(usser.getModified());
        messageResponse.setLastLogin(usser.getLastLogin());
        messageResponse.setToken(usser.getToken());
        messageResponse.setActive(usser.isActive());

        // mensaje que guardo todo correctamente
        return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);

    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Usser update(@RequestBody Usser usser){
        return usserService.update(usser);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return usserService.deleteUser(id);
    }


    //Customs


}

