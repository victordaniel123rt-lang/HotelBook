package com.vdgarcia.HotelBook.integration;


import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.dto.HotelDTO;
import com.vdgarcia.HotelBook.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class HotelServiceTest {
    @Autowired
    private HotelService service;

    @Test
    @Transactional
    void testBuscar_Todos(){
         List<HotelDTO> hoteles = this.service.buscarTodos();
         assertEquals(2,hoteles.size());
     }


     @Test
     @Transactional
    void testBuscarPorId(){
        Long id = 1L;
        HotelDTO hotel = this.service.buscarPorId(id);
        assertNotNull(hotel);
        assertEquals(1L, hotel.getId());
        assertEquals("Hotel Sol y Luna", hotel.getNombre());
     }


     @Test
    void testBuscarPorId_NotFound(){
        Long id = 4L;
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.buscarPorId(id);
        });
        assertEquals("No se encontro el hotel con el id: " + id, exception.getMessage());
     }
     @Test
    @Transactional
    void crearhotel(){
         List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
         HotelDTO hotelDto = new HotelDTO(null, "Los Girasoles", "México", "Calle Morelos 154", "5 Estrellas", habitacionesDTO);
         HotelDTO creado = this.service.crear(hotelDto);
         assertNotNull(creado);
         assertNotNull(creado.getId());
         assertTrue(creado.getId()>0);

     }

     @Test
     @Transactional
    void testActualizarHotel(){
        Long id = 1L;
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        HotelDTO hotelDto = new HotelDTO(id, "Los Girasoles 8", "México", "Calle Morelos 154", "4 Estrellas", habitacionesDTO);
        HotelDTO actualizado = this.service.actualizar(id,hotelDto);

        assertNotNull(actualizado);
        assertEquals(1L, actualizado.getId());
        assertNotEquals("El Sol y Luna", actualizado.getNombre());
        assertNotEquals("5 Estrellas", actualizado.getCategoria());

     }

     @Test
    void testActualizar_NotFound(){
        Long id = 6L;
         List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
         HotelDTO hotelDto = new HotelDTO(id, "Los Girasoles 8", "México", "Calle Morelos 154", "4 Estrellas", habitacionesDTO);
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.actualizar(id,hotelDto);
        });
        assertEquals("No se encontro el hotel con el id: " + id, exception.getMessage());
     }


     @Test
    @Transactional
    void testEliminar(){
        Long id = 1L;
        HotelDTO eliminar = this.service.eliminar(id);
        assertNotNull(eliminar);
     }




}
