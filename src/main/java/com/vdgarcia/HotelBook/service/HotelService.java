package com.vdgarcia.HotelBook.service;

import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    List<HotelDTO> buscarTodos();
    HotelDTO buscarPorId(Long id);
    HotelDTO crear(HotelDTO dto);
    HotelDTO actualizar(Long id, HotelDTO dto);
    HotelDTO eliminar(Long id);
}
