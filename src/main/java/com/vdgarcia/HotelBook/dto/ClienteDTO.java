package com.vdgarcia.HotelBook.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo que representa la información de un cliente")
public class ClienteDTO {
    @Schema(description = "ID único del cliente (autogenerado)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Nombre con el que se identifica el cliente", example = "Víctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    @Schema(description = "Apellido con el que se identifica el cliente", example = "García", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellido;
    @Schema(description = "email personal del cliente", example = "vgarcia@example.com")
    private String email;
    @Schema(description = "Numero telefonico del cliente", example = "555655145")
    private Integer telefono;
    @Schema(description = "DNI personal del cliente", example = "40123123")
    private Long dni;
    private List<ReservacionDTO> reservaciones;
}
