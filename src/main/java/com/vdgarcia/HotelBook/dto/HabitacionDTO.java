package com.vdgarcia.HotelBook.dto;

import com.vdgarcia.HotelBook.entity.Tipo;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitacionDTO {
    private Long id;
    private Integer numero;
    private Tipo tipo;
    private Double precioPorNoche;
    private Boolean disponible;
    private Long hotel;
    private List<ReservacionDTO> reservaciones;
}
