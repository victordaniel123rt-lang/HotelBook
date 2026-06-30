package com.vdgarcia.HotelBook.service;
import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Cliente;

import java.util.List;


public interface ClienteService {
    List<ClienteDTO> buscarTodos();
    ClienteDTO buscarPorId(Long id);
    ClienteDTO crear(ClienteDTO dto);
    ClienteDTO actualizar(Long id, ClienteDTO dto);
    ClienteDTO eliminar(Long id);
}
