package com.vdgarcia.HotelBook.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservacionDTO {
    private Long id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private LocalDate fechaReserva;
    private Boolean estado;
    private Long cliente;
    private Long clieteDni;
    private Long habitacion;
    private Double total;
}
