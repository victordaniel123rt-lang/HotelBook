package com.vdgarcia.HotelBook.integration;


import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.service.HabitacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
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

}
