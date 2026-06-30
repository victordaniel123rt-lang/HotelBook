package com.vdgarcia.HotelBook.service.impl;
import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Cliente;
import com.vdgarcia.HotelBook.mapper.Mapper;
import com.vdgarcia.HotelBook.repository.ClienteRepository;
import com.vdgarcia.HotelBook.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;

    @Override
    public List<ClienteDTO> buscarTodos() {
        return repository.findAll().stream().map(Mapper::toClienteDTO).toList();
    }

    @Override
    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()->new RuntimeException("No se encontro el cliente con el id: " + id));
        return Mapper.toClienteDTO(cliente);
    }

    @Override
    public ClienteDTO crear(ClienteDTO dto) {
        Cliente cliente = Mapper.toCliente(dto);
        Cliente guardado = repository.save(cliente);
        return Mapper.toClienteDTO(guardado);
    }

    @Override
    public ClienteDTO actualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()->new RuntimeException("No se encontro el cliente con el id: " + id));
        Mapper.updateDTOtoEntity(dto,cliente);
        Cliente actualizado = repository.save(cliente);
        return Mapper.toClienteDTO(actualizado);
    }

    @Override
    public ClienteDTO eliminar(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()->new RuntimeException("No se encontro el cliente con el id: " + id));
        repository.delete(cliente);
        return Mapper.toClienteDTO(cliente);
    }

    }

