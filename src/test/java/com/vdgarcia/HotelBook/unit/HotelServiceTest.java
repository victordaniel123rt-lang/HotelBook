package com.vdgarcia.HotelBook.unit;

import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.dto.HotelDTO;
import com.vdgarcia.HotelBook.entity.Habitacion;
import com.vdgarcia.HotelBook.entity.Hotel;
import com.vdgarcia.HotelBook.repository.HotelRepository;
import com.vdgarcia.HotelBook.service.HotelService;
import static org.junit.jupiter.api.Assertions.*;

import com.vdgarcia.HotelBook.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {
    @Mock
    private HotelRepository hotelRepository;
    @InjectMocks
    private HotelServiceImpl hotelService;
    @Test
    void testBuscarTodos_FindAll(){
        List<Habitacion> habitaciones = new ArrayList<>();
        List<Hotel> hoteles= List.of(
                new Hotel(1L, "Las fuentes", "México", "Calle Morelos 154", "5 Estrellas", habitaciones),
                new Hotel(2L, "Las Perlas", "Monterrey", "Calle Ignacio 205", "3 Estrellas", habitaciones)
        );
        when(this.hotelRepository.findAll()).thenReturn(hoteles);
        List<HotelDTO> hotelesDTO = this.hotelService.buscarTodos();
        assertEquals(2,hotelesDTO.size());
        verify(this.hotelRepository,times(1)).findAll();
    }
    @Test
    void testBuscarPorId_FindById(){
        List<Habitacion> habitaciones = new ArrayList<>();
        Hotel hotelDB = new Hotel(1L, "Las fuentes", "México", "Calle Morelos 154", "5 Estrellas", habitaciones);
        when(this.hotelRepository.findById(1L)).thenReturn(Optional.of(hotelDB));
        HotelDTO dto = this.hotelService.buscarPorId(1L);
        assertNotNull(dto);
        assertEquals(1L,dto.getId());
        assertEquals("Las fuentes", dto.getNombre());
        verify(this.hotelRepository,times(1)).findById(1L);
    }
    @Test
    void testBuscarPorId_NotFound(){
        when(this.hotelRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.hotelService.buscarPorId(1L);
        });
        assertEquals("No se encontro el hotel con el id: " + 1L, exception.getMessage());
        verify(this.hotelRepository,times(1)).findById(1L);
    }
    @Test
    void testCrear_HotelBuild(){
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        List<Habitacion> habitaciones = new ArrayList<>();
        Hotel hotelDB = new Hotel(1L, "Las fuentes", "México", "Calle Morelos 154", "5 Estrellas", habitaciones);
        HotelDTO hotelDto = new HotelDTO(1L, "Las fuentes", "México", "Calle Morelos 154", "5 Estrellas", habitacionesDTO);
        when(this.hotelRepository.save(any(Hotel.class))).thenReturn(hotelDB);
        HotelDTO dto = this.hotelService.crear(hotelDto);
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Las fuentes", dto.getNombre());
        verify(this.hotelRepository,times(1)).save(any(Hotel.class));
    }
    @Test
    void testActualizar_HotelFound(){
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        List<Habitacion> habitaciones = new ArrayList<>();
        Hotel hotelDB = new Hotel(1L, "Las fuentes", "México", "Calle Morelos 154", "5 Estrellas", habitaciones);
        Hotel hotel = new Hotel(1L, "Las fuentes 2", "México", "Calle Morelos 200", "5 Estrellas", habitaciones);
        HotelDTO hotelDto = new HotelDTO(1L, "Las fuentes", "México", "Calle Morelos 154", "5 Estrellas", habitacionesDTO);
        when(this.hotelRepository.findById(1L)).thenReturn(Optional.of(hotelDB));
        when(this.hotelRepository.save(any(Hotel.class))).thenReturn(hotel);
        HotelDTO actualizado = this.hotelService.actualizar(1L, hotelDto);
        assertEquals(1L, actualizado.getId());
        assertEquals("Las fuentes 2", actualizado.getNombre());
        assertEquals("Calle Morelos 200", actualizado.getDireccion());
        verify(this.hotelRepository,times(1)).findById(1L);
        verify(this.hotelRepository,times(1)).save(any(Hotel.class));
    }

    @Test
    void testActualizar_HotelNotFound(){
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        HotelDTO hotelDto = new HotelDTO(1L, "Las fuentes", "México", "Calle Morelos 154", "5 Estrellas", habitacionesDTO);
        when(this.hotelRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.hotelService.actualizar(1L,hotelDto);
        });
        assertEquals("No se encontro el hotel con el id: " + 1L, exception.getMessage());
        verify(this.hotelRepository,times(1)).findById(1L);
    }

    @Test
    void testEliminar(){
        List<Habitacion> habitaciones = new ArrayList<>();
        Hotel hotelDB = new Hotel(1L, "Las fuentes", "México", "Calle Morelos 154", "5 Estrellas", habitaciones);
        when(this.hotelRepository.findById(1L)).thenReturn(Optional.of(hotelDB));
        HotelDTO eliminar = this.hotelService.eliminar(1L);
        verify(this.hotelRepository,times(1)).findById(1L);
        verify(this.hotelRepository,times(1)).delete(any(Hotel.class));

    }


    @Test
    void testEliminar_NotFound(){
        when(this.hotelRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
           this.hotelService.eliminar(1L);
        });
        assertEquals("No se encontro el hotel con el id: " + 1L, exception.getMessage());
        verify(this.hotelRepository,times(1)).findById(1L);
    }

    








}
