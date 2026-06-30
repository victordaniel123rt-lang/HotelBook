package com.vdgarcia.HotelBook.service.impl;

import com.vdgarcia.HotelBook.dto.HotelDTO;
import com.vdgarcia.HotelBook.entity.Hotel;
import com.vdgarcia.HotelBook.mapper.Mapper;
import com.vdgarcia.HotelBook.repository.HotelRepository;
import com.vdgarcia.HotelBook.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository repository;

    @Override
    public List<HotelDTO> buscarTodos() {
        return repository.findAll().stream().map(Mapper::toHotelDTO).toList();
    }

    @Override
    public HotelDTO buscarPorId(Long id) {
        Hotel hotel = repository.findById(id).orElseThrow(
                ()->new RuntimeException("No se encontro el hotel con el id: " + id));
        return Mapper.toHotelDTO(hotel);
    }

    @Override
    public HotelDTO crear(HotelDTO dto) {
        Hotel hotel = Mapper.toHotel(dto);
        Hotel guardado = repository.save(hotel);
        return Mapper.toHotelDTO(guardado);
    }

    @Override
    public HotelDTO actualizar(Long id,HotelDTO dto) {
        Hotel hotel = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("No se encontro el hotel con el id: " + id));
        Mapper.updateDtoToEntityHotel(dto,hotel);
        Hotel actualizado = repository.save(hotel);
        return Mapper.toHotelDTO(actualizado);
    }

    @Override
    public HotelDTO eliminar(Long id) {
        Hotel eliminar = repository.findById(id).orElseThrow(
                ()->new RuntimeException("No se encontro el hotel con el id: " + id));
        repository.delete(eliminar);
        return Mapper.toHotelDTO(eliminar);
    }
}
