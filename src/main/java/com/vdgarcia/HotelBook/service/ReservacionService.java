package com.vdgarcia.HotelBook.service;

import com.vdgarcia.HotelBook.dto.HotelDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;

import java.util.List;

public interface ReservacionService {
    List<ReservacionDTO> buscarTodos();
    ReservacionDTO buscarPorId(Long id);
    ReservacionDTO crear(ReservacionDTO dto);
    ReservacionDTO actualizar(ReservacionDTO dto);
    ReservacionDTO eliminar(Long id);
    //ReservacionDTO buscarPorHabitacion(Long habitacionId);
}
