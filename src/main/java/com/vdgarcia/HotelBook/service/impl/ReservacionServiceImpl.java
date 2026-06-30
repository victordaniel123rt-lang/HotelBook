package com.vdgarcia.HotelBook.service.impl;

import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Habitacion;
import com.vdgarcia.HotelBook.entity.Reservacion;
import com.vdgarcia.HotelBook.mapper.Mapper;
import com.vdgarcia.HotelBook.repository.ReservacionRepository;
import com.vdgarcia.HotelBook.service.HabitacionService;
import com.vdgarcia.HotelBook.service.ReservacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservacionServiceImpl implements ReservacionService {
    private final ReservacionRepository repository;
    private final HabitacionService habitacionService;

    @Override
    public List<ReservacionDTO> buscarTodos() {
        return repository.findAll().stream().map(Mapper::toReservacionDTO).toList();
    }

    @Override
    public ReservacionDTO buscarPorId(Long id) {
        Reservacion reserva = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("No se encontro la reservacion con el id: " + id));
        return Mapper.toReservacionDTO(reserva);
    }

    @Override
    @Transactional
    public ReservacionDTO crear(ReservacionDTO dto) {
        // 1. Validar coherencia de fechas (Evita que sean iguales o inversas)
        if (!dto.getFechaSalida().isAfter(dto.getFechaEntrada())) {
            throw new RuntimeException("La fecha de salida debe ser posterior a la fecha de entrada.");
        }

        // 2. Validar existencia de la habitación
        Habitacion habitacion = Mapper.toHabitacion(habitacionService.buscarPorId(dto.getHabitacion()));
        if (habitacion == null) {
            throw new RuntimeException("La habitación solicitada no existe.");
        }

        // 3. Validar traslape directamente en la Base de Datos
        // Cambiamos el método para que devuelva un boolean si encuentra alguna coincidencia
        boolean estaOcupada = repository.existsByHabitacionIdAndFechaEntradaBeforeAndFechaSalidaAfter(
                dto.getHabitacion(), dto.getFechaSalida(), dto.getFechaEntrada());

        if (estaOcupada) {
            throw new RuntimeException("La habitación ya se encuentra ocupada en el rango de fechas solicitado.");
        }

        // 4. Calcular días y total
        long dias = ChronoUnit.DAYS.between(dto.getFechaEntrada(), dto.getFechaSalida());
        double total = dias * habitacion.getPrecioPorNoche();

        // 5. Mapear y preparar entidad
        Reservacion reservacion = Mapper.toReservacion(dto);
        reservacion.setHabitacion(habitacion); // Aseguras la relación
        reservacion.setTotal(total);
        reservacion.setFechaReserva(LocalDate.now()); // Fecha en la que se crea la reserva
        reservacion.setEstado(true); // Activa por defecto

        // Si manejas Cliente, recuerda buscarlo y setearlo aquí también:
        // reservacion.setCliente(clienteService.buscarPorId(dto.getClienteId()));

        // 6. Guardar y retornar
        Reservacion guardado = repository.save(reservacion);
        return Mapper.toReservacionDTO(guardado);
    }

    @Override
    public ReservacionDTO actualizar(ReservacionDTO dto) {
        return null;
    }

    @Override
    public ReservacionDTO eliminar(Long id) {
        Reservacion eliminar = repository.findById(id).orElseThrow(
                ()-> new RuntimeException("No se encontro la reservacion con le id: " + id));
        repository.delete(eliminar);
        return Mapper.toReservacionDTO(eliminar);
    }

}
