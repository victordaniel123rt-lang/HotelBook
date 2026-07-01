package com.vdgarcia.HotelBook.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo que representa la información de una reservación")
public class ReservacionDTO {
    @Schema(description = "ID único de la reservación (autogenerado)", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Fecha en la que el cliente ingresa al hotel", example = "2026-07-15", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaEntrada;
    @Schema(description = "Fecha en la que el cliente deja el hotel", example = "2026-07-20", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaSalida;
    @Schema(description = "Fecha en la que se realizó la reserva", example = "2026-07-01")
    private LocalDate fechaReserva;
    @Schema(description = "Estado actual de la reserva (true = Activa, false = Cancelada)", example = "true")
    private Boolean estado;
    @Schema(description = "ID del cliente que realiza la reserva", example = "2")
    private Long cliente;
    @Schema(description = "DNI del cliente para verificación rápida", example = "40123456")
    private Long clieteDni;
    @Schema(description = "ID de la habitación reservada", example = "45")
    private Long habitacion;
    @Schema(description = "Monto total de la estadía", example = "425.00")
    private Double total;
}
