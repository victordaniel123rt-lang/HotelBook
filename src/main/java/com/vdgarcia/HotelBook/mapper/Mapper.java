package com.vdgarcia.HotelBook.mapper;

import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.dto.HotelDTO;
import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.entity.Cliente;
import com.vdgarcia.HotelBook.entity.Habitacion;
import com.vdgarcia.HotelBook.entity.Hotel;
import com.vdgarcia.HotelBook.entity.Reservacion;

import java.util.List;

public class Mapper {
    public static HabitacionDTO toHabitacionDTO(Habitacion entity){
        if(entity==null) return null;
        List<ReservacionDTO> lista = entity.getReservaciones().stream().map(Mapper::toReservacionDTO).toList();
        return HabitacionDTO.builder()
                .id(entity.getId())
                .hotel(entity.getHotel().getId())
                .tipo(entity.getTipo())
                .disponible(entity.getDisponible())
                .numero(entity.getNumero())
                .precioPorNoche(entity.getPrecioPorNoche())
                .reservaciones(lista)
                .build();
    }
    public static ReservacionDTO toReservacionDTO(Reservacion entity){
        if(entity == null) return null;
         return ReservacionDTO.builder()
                 .id(entity.getId())
                 .cliente(entity.getCliente().getId())
                 .estado(entity.getEstado())
                 .fechaEntrada(entity.getFechaEntrada())
                 .fechaSalida(entity.getFechaSalida())
                 .fechaReserva(entity.getFechaReserva())
                 .habitacion(entity.getHabitacion().getId())
                 .clieteDni(entity.getCliente().getDni())
                 .total(entity.getTotal())
                 .build();
    }
    public static ClienteDTO toClienteDTO(Cliente entity){
        if(entity==null) return null;
        List<ReservacionDTO> lista = entity.getReservaciones().stream().map(Mapper::toReservacionDTO).toList();
        return ClienteDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .reservaciones(lista)
                .dni(entity.getDni())
                .build();
    }

    public static HotelDTO toHotelDTO(Hotel entity){
        if(entity==null) return null;
        List<HabitacionDTO> lista = entity.getHabitaciones().stream().map(Mapper::toHabitacionDTO).toList();
        return HotelDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .categoria(entity.getCategoria())
                .ciudad(entity.getCiudad())
                .direccion(entity.getDireccion())
                .habitaciones(lista)
                .build();
    }

    public static Habitacion toHabitacion(HabitacionDTO dto){
        if(dto==null) return null;
        List<Reservacion> lista = dto.getReservaciones().stream().map(Mapper::toReservacion).toList();
        return Habitacion.builder()
                .id(dto.getId())
                .numero(dto.getNumero())
                .tipo(dto.getTipo())
                .disponible(dto.getDisponible())
                .precioPorNoche(dto.getPrecioPorNoche())
                .hotel(
                        Hotel.builder().id(dto.getHotel()).build()
                )
                .reservaciones(lista)
                .build();
    }

    public static Reservacion toReservacion(ReservacionDTO dto){
        if(dto==null) return null;
        return Reservacion.builder()
                .id(dto.getId())
                .cliente(
                        Cliente.builder().id(dto.getCliente()).build()
                )
                .habitacion(
                        Habitacion.builder().id(dto.getHabitacion()).build()
                )
                .estado(dto.getEstado())
                .fechaSalida(dto.getFechaSalida())
                .fechaEntrada(dto.getFechaEntrada())
                .build();

    }

    public static Cliente toCliente(ClienteDTO dto){
        if(dto==null) return null;
        List<Reservacion> lista = dto.getReservaciones().stream().map(Mapper::toReservacion).toList();
        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .reservaciones(lista)
                .dni(dto.getDni())
                .build();
    }


    public static Hotel toHotel(HotelDTO dto){
        if(dto==null) return null;
        List<Habitacion> lista = dto.getHabitaciones().stream().map(Mapper::toHabitacion).toList();
        return Hotel.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .categoria(dto.getCategoria())
                .direccion(dto.getDireccion())
                .habitaciones(lista)
                .build();
    }


    public static void updateDTOtoEntity(ClienteDTO dto, Cliente entity){
        if(dto ==null || entity==null) return;
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
    }


    public static void updateDTOtoEntityHabitacion(HabitacionDTO dto, Habitacion entity){
        if(dto==null || entity ==null) return;
        entity.setNumero(dto.getNumero());
        entity.setPrecioPorNoche(dto.getPrecioPorNoche());
        entity.setHotel(Hotel.builder().id(dto.getId()).build());
        entity.setTipo(dto.getTipo());
    }


    public static void updateDtoToEntityHotel(HotelDTO dto, Hotel entity){
        if(dto==null || entity==null) return;
        entity.setNombre(dto.getNombre());
        entity.setCiudad(dto.getCiudad());
        entity.setCategoria(dto.getCategoria());
        entity.setDireccion(dto.getDireccion());
    }




}
