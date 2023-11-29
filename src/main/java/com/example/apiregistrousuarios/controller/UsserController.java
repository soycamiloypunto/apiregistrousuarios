package com.example.apiregistrousuarios.controller;

import com.example.apiregistrousuarios.entity.Usser;
import com.example.apiregistrousuarios.messages.CustomErrorResponse;
import com.example.apiregistrousuarios.messages.CustomMessageResponse;
import com.example.apiregistrousuarios.service.UsserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            //System.out.println("El correo ya registrado");

            // Creo el mensaje de error
            CustomErrorResponse errorResponse = new CustomErrorResponse();
            errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
            //errorResponse.setMessage("El correo ya registrado para el usuario " + usser.getName());
            errorResponse.setMessage("El correo ya está registrado");
            // Devuelvo el mensaje de error
            return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } else {

            // Agregar los campos nuevos
            usser.setCreated(LocalDateTime.now());
            usser.setModified(LocalDateTime.now());
            usser.setLastLogin(LocalDateTime.now());
            usser.setToken(UUID.randomUUID().toString());
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

            // Devuelvo el mensaje que todo se guardó correctamente
            return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);

            // Mensaje automático de guardado.
            //return ResponseEntity.status(HttpStatus.CREATED).body(savedUsser);
        }
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Usser update(@RequestBody Usser usser){
        return usserService.update(usser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return usserService.deleteUser(id);
    }


    //Customs


}

