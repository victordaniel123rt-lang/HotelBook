package com.vdgarcia.HotelBook.service;

import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.dto.HabitacionDTO;

import java.util.List;

public interface HabitacionService {
    List<HabitacionDTO> buscarTodos();
    HabitacionDTO buscarPorId(Long id);
    HabitacionDTO crear(HabitacionDTO dto);
    HabitacionDTO actualizar(Long id, HabitacionDTO dto);
    HabitacionDTO eliminar(Long id);
}
