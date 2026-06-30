package com.vdgarcia.HotelBook.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {
    private Long id;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String categoria;
    private List<HabitacionDTO> habitaciones;
}
