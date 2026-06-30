package com.vdgarcia.HotelBook.service.impl;

import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.entity.Habitacion;
import com.vdgarcia.HotelBook.mapper.Mapper;
import com.vdgarcia.HotelBook.repository.HabitacionRepository;
import com.vdgarcia.HotelBook.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HabitacionServiceImpl implements HabitacionService {
    private final HabitacionRepository repository;

    @Override
    public List<HabitacionDTO> buscarTodos() {
        return repository.findAll().stream().map(Mapper::toHabitacionDTO).toList();
    }

    @Override
    public HabitacionDTO buscarPorId(Long id) {
        Habitacion habitacion = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("No se encontro la habitacion con el id: " + id)
        );
        return Mapper.toHabitacionDTO(habitacion);
    }

    @Override
    public HabitacionDTO crear(HabitacionDTO dto) {
        Habitacion habitacion = Mapper.toHabitacion(dto);
        Habitacion guardado = repository.save(habitacion);
        return Mapper.toHabitacionDTO(guardado);
    }

    @Override
    public HabitacionDTO actualizar(Long id, HabitacionDTO dto) {
        Habitacion habitacion = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("No se encontro la habitacion con el id: " + id));
        Mapper.updateDTOtoEntityHabitacion(dto,habitacion);
        Habitacion actualizado = repository.save(habitacion);
        return Mapper.toHabitacionDTO(actualizado);
    }

    @Override
    public HabitacionDTO eliminar(Long id) {
        Habitacion eliminar = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("No se encontro la habitacion con el id: " + id));
        repository.delete(eliminar);
        return Mapper.toHabitacionDTO(eliminar);
    }
}
