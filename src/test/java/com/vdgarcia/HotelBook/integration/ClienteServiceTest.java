package com.vdgarcia.HotelBook.integration;

import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Cliente;
import com.vdgarcia.HotelBook.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;

    @Test
    @Transactional
    void testFindClienteById_ClienteExists(){
        Long id = 1L;
        ClienteDTO clienteDTO = this.clienteService.buscarPorId(id);
        assertNotNull(clienteDTO);
        assertEquals("María",clienteDTO.getNombre());
    }

    @Test
    void testFindClienteById_ClienteNotFound(){
        Long id = 3L;
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.clienteService.buscarPorId(id);
        });
        assertEquals("No se encontro el cliente con el id: " + id, exception.getMessage());
    }

    @Test
    @Transactional
    void testBuscarTodos(){
        List<ClienteDTO> clientes = this.clienteService.buscarTodos();
        assertEquals(2,clientes.size());
    }


    @Test
    @Transactional
    void testcrear_CategoriCreate(){
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        ClienteDTO cliente = new ClienteDTO("Victor","Elacio","vg@example.com",215632L, 1234567891,reservacionesDTO);
        ClienteDTO cliente1 = this.clienteService.crear(cliente);
        assertNotNull(cliente1);
        assertNotNull(cliente1.getId());
        assertTrue(cliente1.getId() > 0);
        assertEquals("Victor", cliente1.getNombre());
    }


    @Test
    @Transactional
    void testactualizar_CategoriaUpdate(){
        Long id = 2L;
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        ClienteDTO cliente = new ClienteDTO("Víctor","Garcia","vgarciaz@example.com",40123123L, 1234567891,reservacionesDTO);
        ClienteDTO actualizado = this.clienteService.actualizar(id,cliente);
        assertNotNull(actualizado);
        assertEquals(2L, actualizado.getId());

    }

    @Test
    @Transactional
    void testactualizar_ClienteNotUpdate(){
        Long id = 4L;
        List<ReservacionDTO> reservacionesDTO = new ArrayList<>();
        ClienteDTO cliente = new ClienteDTO("Víctor","Garcia","vgarciaz@example.com",40123123L, 1234567891,reservacionesDTO);
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.clienteService.actualizar(id,cliente);
        });
        assertEquals("No se encontro el cliente con el id: " + id, exception.getMessage());

    }

    @Test
    @Transactional
    void testeliminar_ClienteEliminado(){
        Long id = 1L;
        ClienteDTO eliminar = this.clienteService.eliminar(id);
        assertNotNull(eliminar);
    }

    @Test
    @Transactional
    void testeliminar_ClienteNotFound(){
        Long id = 5L;
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            this.clienteService.eliminar(id);
        });
        assertEquals("No se encontro el cliente con el id: " + id, exception.getMessage());
    }







}
