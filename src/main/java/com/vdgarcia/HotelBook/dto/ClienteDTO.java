package com.vdgarcia.HotelBook.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer telefono;
    private Long dni;
    private List<ReservacionDTO> reservaciones;
}
