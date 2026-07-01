package com.vdgarcia.HotelBook.unit;


import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Habitacion;
import com.vdgarcia.HotelBook.entity.Hotel;
import com.vdgarcia.HotelBook.entity.Reservacion;
import com.vdgarcia.HotelBook.repository.HabitacionRepository;
import com.vdgarcia.HotelBook.service.HabitacionService;
import static org.junit.jupiter.api.Assertions.*;

import com.vdgarcia.HotelBook.service.impl.HabitacionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vdgarcia.HotelBook.entity.Tipo.SUPERIOR;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HabitacionServiceTest {
    @Mock
    HabitacionRepository habitacionRepository;
    @InjectMocks
    HabitacionServiceImpl habitacionService;

    @Test
    void testBuscarPorId_HabitacionFound(){
        List<Reservacion> reservaciones = new ArrayList<>();
        Hotel hotel = new Hotel();
        Habitacion habitacionDB = new Habitacion(1L,105,SUPERIOR,1505.2,true,hotel,reservaciones);
        when(this.habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacionDB));

        HabitacionDTO dto = habitacionService.buscarPorId(1L);
        assertNotNull(dto);
        assertEquals(1505.2, dto.getPrecioPorNoche());
        verify(this.habitacionRepository,times(1)).findById(1L);
    }

    @Test
    void testBuscarPorId_HabitacionNotFound(){
        when(this.habitacionRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
        this.habitacionService.buscarPorId(1L);
        });
        assertEquals("No se encontro la habitacion con el id: " + 1L, exception.getMessage());
        verify(this.habitacionRepository, times(1)).findById(1L);

    }

    @Test
    void testBuscarTodos_FindAllHabitaciones(){
        List<Reservacion> reservaciones = new ArrayList<>();
        Hotel hotel = new Hotel();
        List<Habitacion> habitaciones = List.of(
                new Habitacion(1L,105,SUPERIOR,1505.2,true,hotel,reservaciones),
                new Habitacion(1L,108,SUPERIOR,2022.2,false,hotel,reservaciones)
        );
        when(this.habitacionRepository.findAll()).thenReturn(habitaciones);

        List<HabitacionDTO> dtos = this.habitacionService.buscarTodos();
        assertEquals(2, dtos.size());
        verify(this.habitacionRepository,times(1)).findAll();

    }

    @Test
    void testCrear_CrearHabitacion(){
        List<Reservacion> reservaciones = new ArrayList<>();
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        Hotel hotel = new Hotel();
        Habitacion habitacionDB = new Habitacion(1L,105,SUPERIOR,1505.2,true,hotel,reservaciones);
        HabitacionDTO habitacionDBDTO = new HabitacionDTO(1L,105,SUPERIOR,1505.2,true,hotel.getId(),reservacionesDTO);
        when(this.habitacionRepository.save(any(Habitacion.class))).thenReturn(habitacionDB);
        HabitacionDTO nuevo = this.habitacionService.crear(habitacionDBDTO);

        assertNotNull(nuevo);
        assertEquals(1L,nuevo.getId());
        verify(this.habitacionRepository,times(1)).save(any(Habitacion.class));
    }





}
