package com.vdgarcia.HotelBook.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.yaml.snakeyaml.events.ScalarEvent;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo que representa la información de un hotel")
public class HotelDTO {
    @Schema(description = "ID único del hotel (autogenerado)", example = "3", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Nombre con el que ubica el hotel", example = "Los cedros", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    @Schema(description = "Ciudad en la que se encuentra ubicado el hotel", example = "México", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ciudad;
    @Schema(description = "Dirección principal del hotel", example = "Calle Las Heras 550", requiredMode = Schema.RequiredMode.REQUIRED)
    private String direccion;
    @Schema(description = "Nivel de categoria del hotel", example = "3 ESTRELLAS", requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoria;
    private List<HabitacionDTO> habitaciones;
}
