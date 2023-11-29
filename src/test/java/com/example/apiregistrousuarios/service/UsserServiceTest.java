package com.example.apiregistrousuarios.service;

import com.example.apiregistrousuarios.entity.Phone;
import com.example.apiregistrousuarios.entity.Usser;
import com.example.apiregistrousuarios.repository.UsserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UsserServiceTest {

    @Mock
    private UsserRepository usserRepository;
    @InjectMocks
    private UsserService usserService;

    private Usser usser;
    private Phone phone;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usser= new Usser();
        usser.setActive(true);
        usser.setName("Cristian Camilo");
        usser.setEmail("mail@mail.com");
        usser.setPassword("Hola1234");
        usser.setId((123));
        usser.setPhones((List<Phone>) phone);

        phone= new Phone();
        phone.setNumber("3135551212");
        phone.setUsser(usser);



    }

    @org.junit.jupiter.api.Test
    void getAll() {
        when(usserRepository.getAll()).thenReturn(asList(usser));
        assertNotNull(usserService.getAll());
    }

    @org.junit.jupiter.api.Test
    void save() {
        when(usserRepository.save(any(Usser.class))).thenReturn(usser);
        assertNotNull(usserService.save(new Usser()));
    }

    @Test
    void findByEmail() {
        // Define el comportamiento del mock
        when(usserRepository.findByEmail("mail@mail.com")).thenReturn(Collections.emptyList());

        // Llama al método del servicio
        List<Usser> ussers = usserService.findByEmail("mail@mail.com");

        // Verifica el resultado
        assertEquals(0, ussers.size());
    }

    @Test
    void existsByEmail(){
        when(usserRepository.findByEmail("mail@mail.com")).thenReturn(asList(new Usser()));
        // Llama al método del servicio
        List<Usser> exists = usserService.findByEmail("mail@mail.com");
        // Verifica el resultado
        assertEquals(1, exists.size());
    }
}