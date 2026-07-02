package com.vdgarcia.HotelBook.integration;


import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Cliente;
import com.vdgarcia.HotelBook.entity.Hotel;
import com.vdgarcia.HotelBook.service.ReservacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.vdgarcia.HotelBook.entity.Tipo.SUPERIOR;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ReservacionServiceTest {
    @Autowired
    private ReservacionService service;

    @Test
    @Transactional
    void testBuscarTodos(){
        List<ReservacionDTO> reservaciones = this.service.buscarTodos();
        assertEquals(1,reservaciones.size());
    }


    @Test
    @Transactional
    void buscarPorId(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("15-07-26",formato);
        Long id = 1L;
        ReservacionDTO dto = this.service.buscarPorId(id);
        assertNotNull(dto);
        assertEquals(425.0, dto.getTotal());
        assertEquals(fechaEntrada,dto.getFechaEntrada());
    }


    @Test
    @Transactional
    void testBuscarPorId_NotFound(){
        Long id = 5L;
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.buscarPorId(id);
        });
        assertEquals("No se encontro la reservacion con el id: " + id, exception.getMessage());
    }


    @Test
    @Transactional
    void crearReservacion(){
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        Hotel hoteL = new Hotel();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("25-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("29-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);
        Cliente cliente = new Cliente();
        HabitacionDTO habitacionDTO = new HabitacionDTO(3L,105,SUPERIOR,1505.2,true, hoteL.getId(),reservacionesDTO);
        ReservacionDTO reservacionDTO = new ReservacionDTO(null,fechaEntrada,fechaSalida,fechaReserva,true,cliente.getId(),cliente.getDni(),3L,425.0);
        ReservacionDTO guardado = this.service.crear(reservacionDTO);
        assertNotNull(guardado);
        assertNotNull(guardado.getId());
        assertNotEquals("2026-07-25", guardado.getFechaEntrada());
    }

    @Test
    @Transactional
    void testCrear_FirstException(){
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        Hotel hoteL = new Hotel();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("29-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("25-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);
        Cliente cliente = new Cliente();
        HabitacionDTO habitacionDTO = new HabitacionDTO(3L,105,SUPERIOR,1505.2,true, hoteL.getId(),reservacionesDTO);
        ReservacionDTO reservacionDTO = new ReservacionDTO(null,fechaEntrada,fechaSalida,fechaReserva,true,cliente.getId(),cliente.getDni(),3L,425.0);
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.crear(reservacionDTO);
        });
        assertEquals("La fecha de salida debe ser posterior a la fecha de entrada.", exception.getMessage());

    }
    @Test
    @Transactional
    void testCrear_SecondException(){
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        Hotel hoteL = new Hotel();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("20-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);
        Cliente cliente = new Cliente();
        HabitacionDTO habitacionDTO = new HabitacionDTO(3L,105,SUPERIOR,1505.2,true, hoteL.getId(),reservacionesDTO);
        ReservacionDTO reservacionDTO = new ReservacionDTO(null,fechaEntrada,fechaSalida,fechaReserva,true,cliente.getId(),cliente.getDni(),3L,425.0);
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.crear(reservacionDTO);
        });
        assertEquals("La habitación ya se encuentra ocupada en el rango de fechas solicitado.", exception.getMessage());

    }
    @Test
    @Transactional
    void testCrear_ThirdException(){
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        Hotel hoteL = new Hotel();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("20-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);
        Cliente cliente = new Cliente();
        HabitacionDTO habitacionDTO = new HabitacionDTO(3L,105,SUPERIOR,1505.2,true, hoteL.getId(),reservacionesDTO);
        ReservacionDTO reservacionDTO = new ReservacionDTO(null,fechaEntrada,fechaSalida,fechaReserva,true,cliente.getId(),cliente.getDni(),1L,425.0);
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.crear(reservacionDTO);
        });
        assertEquals("No se encontro la habitacion con el id: " + 1L, exception.getMessage());
    }

}
