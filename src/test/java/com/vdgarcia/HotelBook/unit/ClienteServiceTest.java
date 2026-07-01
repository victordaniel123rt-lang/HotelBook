package com.vdgarcia.HotelBook.unit;

import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Cliente;
import com.vdgarcia.HotelBook.entity.Reservacion;
import com.vdgarcia.HotelBook.repository.ClienteRepository;
import com.vdgarcia.HotelBook.service.impl.ClienteServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
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
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void testFindClienteById_ClienteExists(){
        List<Reservacion> reservaciones = new ArrayList<>();
        Cliente clienteDB = new Cliente(1L,"Victor","Garcia","vg@example.com",1234567891,215632L,reservaciones);
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(clienteDB));

        ClienteDTO cliente = this.clienteService.buscarPorId(1L);

        assertNotNull(cliente);
        assertEquals("Victor", cliente.getNombre());
        verify(this.clienteRepository,times(1)).findById(1L);
    }

    @Test
    void testFindClienteById_ClienteNotFound(){
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            this.clienteService.buscarPorId(1L);
        });
        assertEquals("No se encontro el cliente con el id: " + 1L, excepcion.getMessage());
        verify(this.clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarTodos(){
        List<Reservacion> reservaciones = new ArrayList<>();
        List<Cliente> clientesDB = List.of(
                new Cliente(1L,"Victor","Garcia","vg@example.com",1234567891,215632L,reservaciones),
                new Cliente(1L,"Ana","Perez","ap@example.com",1234567147,215669L,reservaciones)
        );
        when(this.clienteRepository.findAll()).thenReturn(clientesDB);
        List<ClienteDTO> clienteDTOS = this.clienteService.buscarTodos();
        assertNotNull(clienteDTOS);
        assertEquals(2,clienteDTOS.size());
        verify(this.clienteRepository,times(1)).findAll();
    }

    @Test
    void testcrear_CategoriCreate(){
        List<Reservacion> reservaciones = new ArrayList<>();
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        ClienteDTO clienteDB = new ClienteDTO(1L,"Victor","Garcia","vg@example.com",1234567891,215632L,reservacionesDTO);
        Cliente cliente = new Cliente(1L,"Victor","Garcia","vg@example.com",1234567891,215632L,reservaciones);
        when(this.clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ClienteDTO clienteDTO = this.clienteService.crear(clienteDB);

        assertNotNull(clienteDTO);
        assertEquals(1L,clienteDTO.getId());
        assertEquals("Victor",clienteDTO.getNombre());
        verify(this.clienteRepository,times(1)).save(any(Cliente.class));
    }

    @Test
    void testactualizar_CategoriaUpdate(){
        List<Reservacion> reservaciones = new ArrayList<>();
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        Cliente cliente = new Cliente(1L,"Victor","Garcia","vg@example.com",1234567891,215632L,reservaciones);
        ClienteDTO clienteDB = new ClienteDTO(1L,"Víctor","García","vg@example.com",1234567891,215632L,reservacionesDTO);
        Cliente clienteActualizado = new Cliente(1L,"Víctor","García","vg@example.com",1234567891,215632L,reservaciones);
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(this.clienteRepository.save(any(Cliente.class))).thenReturn(clienteActualizado);

        ClienteDTO nuevo = this.clienteService.actualizar(1L,clienteDB);
        assertNotNull(nuevo);
        assertEquals(1L,nuevo.getId());
        assertEquals("Víctor",nuevo.getNombre());
        assertEquals("García", nuevo.getApellido());
        verify(this.clienteRepository,times(1)).save(any(Cliente.class));
    }

    @Test
    void testactualizar_ClienteNotUpdate(){
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.clienteService.buscarPorId(1L);
        });
        assertEquals("No se encontro el cliente con el id: " + 1L, exception.getMessage());
        verify(this.clienteRepository,times(1)).findById(1L);
    }

    @Test
    void testeliminar_ClienteEliminado(){
        List<Reservacion> reservaciones = new ArrayList<>();
        Cliente clienteDB = new Cliente(1L,"Victor","Garcia","vg@example.com",1234567891,215632L,reservaciones);
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(clienteDB));


        ClienteDTO result = this.clienteService.eliminar(1L);
        assertNotNull(result);
        verify(this.clienteRepository,times(1)).findById(1L);
        verify(this.clienteRepository, times(1)).delete(clienteDB);

    }


    @Test
    void testEliminar_ClienteNotFound(){
        when(this.clienteRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.clienteService.buscarPorId(1L);
        });

        assertEquals("No se encontro el cliente con el id: " + 1L, exception.getMessage());
        verify(this.clienteRepository,times(1)).findById(1L);

    }







}
