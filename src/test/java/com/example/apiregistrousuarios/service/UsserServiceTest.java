package com.example.apiregistrousuarios.service;

import com.example.apiregistrousuarios.entity.Phone;
import com.example.apiregistrousuarios.entity.Usser;
import com.example.apiregistrousuarios.repository.UsserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    /*
    conjunto de pruebas verifica que el método findByEmail() de la clase usserService devuelve una lista vacía cuando se le pasa una dirección de correo electrónico que no existe en la base de datos.
     */
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

    @Test
    void deleteUser() {
        // Create a mock UsserRepository
        UsserRepository mockUsserRepository = Mockito.mock(UsserRepository.class);

        // Create a UsserService instance with the mock UsserRepository
        UsserService usserService = new UsserService();

        // Create a mock Usser object
        Usser mockUsser = Mockito.mock(Usser.class);

        // When the UsserRepository.getUser() method is called with id 1, return the mock Usser object
        Mockito.when(mockUsserRepository.getUser(1)).thenReturn(Optional.of(mockUsser));

        // Call the deleteUser() method
        usserService.deleteUser(1);

        // Verify that the UsserRepository.delete() method was called
        Mockito.verify(mockUsserRepository, Mockito.times(1)).delete(mockUsser);
    }
}