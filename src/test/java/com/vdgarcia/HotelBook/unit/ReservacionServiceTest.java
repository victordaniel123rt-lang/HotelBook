package com.vdgarcia.HotelBook.unit;

import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.dto.HotelDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Cliente;
import com.vdgarcia.HotelBook.entity.Habitacion;
import com.vdgarcia.HotelBook.entity.Hotel;
import com.vdgarcia.HotelBook.entity.Reservacion;
import com.vdgarcia.HotelBook.repository.ReservacionRepository;
import com.vdgarcia.HotelBook.service.impl.HabitacionServiceImpl;
import com.vdgarcia.HotelBook.service.impl.ReservacionServiceImpl;

import static com.vdgarcia.HotelBook.entity.Tipo.SUPERIOR;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReservacionServiceTest {

    @Mock
    private ReservacionRepository repository;
    @Mock
    private HabitacionServiceImpl habitacionService;
    @InjectMocks
    private ReservacionServiceImpl service;


    @Test
    void testBuscar_FindAll(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("20-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);

        Cliente cliente = new Cliente();
        Habitacion habitacion = new Habitacion();
        List<Reservacion> reservaciones = List.of(
                new Reservacion(1L,fechaEntrada,fechaSalida,fechaReserva,true,425.0,cliente, habitacion),
                new Reservacion(2L,fechaEntrada,fechaSalida,fechaReserva,false,456.0,cliente, habitacion)
        );
        when(this.repository.findAll()).thenReturn(reservaciones);

        List<ReservacionDTO> lista = this.service.buscarTodos();

        assertNotNull(lista);
        assertEquals(2,lista.size());
        verify(this.repository,times(1)).findAll();
    }

    @Test
    void buscarPorId_Found(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("20-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);
        Cliente cliente = new Cliente();
        Habitacion habitacion = new Habitacion();
        Reservacion reservacion = new Reservacion(1L,fechaEntrada,fechaSalida,fechaReserva,true,425.0,cliente, habitacion);
        when(this.repository.findById(1L)).thenReturn(Optional.of(reservacion));
        ReservacionDTO reservaDTO = this.service.buscarPorId(1L);
        assertNotNull(reservaDTO);
        assertEquals(1L, reservaDTO.getId());
        assertEquals(425.0,reservaDTO.getTotal());
        verify(this.repository,times(1)).findById(1L);
    }

    @Test
    void buscarPorId_NotFound(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.buscarPorId(1L);
        });
        assertEquals("No se encontro la reservacion con el id: " + 1L, exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testCrear(){
        List<Reservacion> reservaciones = new ArrayList<>();
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        Hotel hoteL = new Hotel();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("20-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);
        Cliente cliente = new Cliente();
        Habitacion habitacion = new Habitacion(1L,105,SUPERIOR,1505.2,true,hoteL,reservaciones);
        HabitacionDTO habitacionDTO = new HabitacionDTO(1L,105,SUPERIOR,1505.2,true, hoteL.getId(),reservacionesDTO);
        Reservacion reservacion = new Reservacion(1L,fechaEntrada,fechaSalida,fechaReserva,true,425.0,cliente, habitacion);
        ReservacionDTO reservacionDTO = new ReservacionDTO(1L,fechaEntrada,fechaSalida,fechaReserva,true,cliente.getId(),cliente.getDni(),habitacion.getId(),425.0);
        when(this.habitacionService.buscarPorId(1L)).thenReturn(habitacionDTO);
        when(this.repository.existsByHabitacionIdAndFechaEntradaBeforeAndFechaSalidaAfter(1L,fechaSalida,fechaEntrada)).thenReturn(false);
        when(this.repository.save(any(Reservacion.class))).thenReturn(reservacion);
        ReservacionDTO guardado = this.service.crear(reservacionDTO);
        assertNotNull(guardado);
        assertEquals(1L, guardado.getId());
        assertEquals(fechaEntrada,guardado.getFechaEntrada());
        verify(this.repository,times(1)).existsByHabitacionIdAndFechaEntradaBeforeAndFechaSalidaAfter(1L,fechaSalida,fechaEntrada);
        verify(this.repository,times(1)).save(any(Reservacion.class));
        verify(this.habitacionService,times(1)).buscarPorId(1L);
    }

    @Test
    void testCrear_FisrtException(){
        List<Reservacion> reservaciones = new ArrayList<>();
        Hotel hoteL = new Hotel();
        Habitacion habitacion = new Habitacion(1L,105,SUPERIOR,1505.2,true,hoteL,reservaciones);
        Cliente cliente = new Cliente();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("20-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);
        ReservacionDTO reservacionDTO = new ReservacionDTO(1L,fechaEntrada,fechaSalida,fechaReserva,true,cliente.getId(),cliente.getDni(),habitacion.getId(),425.0);
        when(this.habitacionService.buscarPorId(1L)).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.crear(reservacionDTO);
        });
        assertEquals("La habitación solicitada no existe.", exception.getMessage());
        verify(this.habitacionService,times(1)).buscarPorId(1L);
    }


    @Test
    void testCrear_SecondException(){
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        List<Reservacion> reservaciones = new ArrayList<>();
        Hotel hoteL = new Hotel();
        Habitacion habitacion = new Habitacion(1L,105,SUPERIOR,1505.2,true,hoteL,reservaciones);
        HabitacionDTO habitacionDTO = new HabitacionDTO(1L,105,SUPERIOR,1505.2,true, hoteL.getId(),reservacionesDTO);
        Cliente cliente = new Cliente();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate fechaEntrada = LocalDate.parse("15-07-26",formato);
        LocalDate fechaSalida = LocalDate.parse("20-07-26",formato);
        LocalDate fechaReserva = LocalDate.parse("01-07-26",formato);
        ReservacionDTO reservacionDTO = new ReservacionDTO(1L,fechaEntrada,fechaSalida,fechaReserva,true,cliente.getId(),cliente.getDni(),habitacion.getId(),425.0);
        when(this.habitacionService.buscarPorId(1L)).thenReturn(habitacionDTO);
        when(this.repository.existsByHabitacionIdAndFechaEntradaBeforeAndFechaSalidaAfter(1L,fechaSalida, fechaEntrada)).thenReturn(true);
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.service.crear(reservacionDTO);
        });
        assertEquals("La habitación ya se encuentra ocupada en el rango de fechas solicitado.", exception.getMessage());
        verify(this.repository,times(1)).existsByHabitacionIdAndFechaEntradaBeforeAndFechaSalidaAfter(1L, fechaSalida, fechaEntrada);
    }







}
