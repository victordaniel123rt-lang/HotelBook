package com.vdgarcia.HotelBook.integration;


import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Habitacion;
import com.vdgarcia.HotelBook.entity.Hotel;
import com.vdgarcia.HotelBook.entity.Reservacion;
import com.vdgarcia.HotelBook.service.HabitacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.vdgarcia.HotelBook.entity.Tipo.SUPERIOR;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class HabitacionServiceTest {
    @Autowired
    private HabitacionService service;


    @Test
    @Transactional
    void testBuscarTodos(){
        List<HabitacionDTO> habitaciones = this.service.buscarTodos();
        assertEquals(1,habitaciones.size());
    }

    @Test
    @Transactional
    void testBuscarPorId(){
        Long id = 3L;
        HabitacionDTO habitacion = this.service.buscarPorId(id);
        assertNotNull(habitacion);
        assertEquals(105, habitacion.getNumero());
        assertEquals(3L,habitacion.getId());
    }

    @Test
    void testBuscarPorId_NotFound(){
        Long id = 1L;
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.buscarPorId(id);
        });
        assertEquals("No se encontro la habitacion con el id: " + id, exception.getMessage());
    }


    @Test
    @Transactional
    void testCrearHabitacion(){
        List<ReservacionDTO> reservaciones = new ArrayList<>();
        Hotel hotel = new Hotel();
        HabitacionDTO habitacionDB = new HabitacionDTO(null,105,SUPERIOR,1505.2,true,hotel.getId(),reservaciones);
        HabitacionDTO habitacionDTO = this.service.crear(habitacionDB);
        assertNotNull(habitacionDTO);
        assertNotNull(habitacionDTO.getId());
        assertTrue(habitacionDTO.getId()>0);
    }


    @Test
    @Transactional
    void testActualizar(){
        Long id = 3L;
        List<ReservacionDTO> reservaciones = new ArrayList<>();
        Hotel hotel = new Hotel();
        HabitacionDTO habitacionDB = new HabitacionDTO(3L,106,SUPERIOR,100.0,true,hotel.getId(),reservaciones);
        HabitacionDTO actualizado = this.service.actualizar(id,habitacionDB);
        assertNotNull(actualizado);
        assertEquals(106,actualizado.getNumero());
        assertEquals(100.0,actualizado.getPrecioPorNoche());
        assertEquals(3L, actualizado.getId());
    }


    @Test
    @Transactional
    void testActualizar_NotFound(){
        Long id = 1L;
        List<ReservacionDTO> reservaciones = new ArrayList<>();
        Hotel hotel = new Hotel();
        HabitacionDTO habitacionDB = new HabitacionDTO(3L,106,SUPERIOR,100.0,true,hotel.getId(),reservaciones);
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.actualizar(id,habitacionDB);
        });

        assertEquals("No se encontro la habitacion con el id: " + id, exception.getMessage());
    }

    @Test
    @Transactional
    void testEliminar(){
        Long id = 3L;
        HabitacionDTO eliminado = this.service.eliminar(id);
        assertNotNull(eliminado);
    }

    @Test
    @Transactional
    void testEliminar_NotFound(){
        Long id = 1L;
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.eliminar(id);
        });
        assertEquals("No se encontro la habitacion con el id: " + id, exception.getMessage());
    }




}
