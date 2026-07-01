package com.vdgarcia.HotelBook.dto;

import com.vdgarcia.HotelBook.entity.Tipo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Modelo que representa la información de una habitación")
public class HabitacionDTO {
    @Schema(description = "ID único de la habitación (autogenerado)", example = "2", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Numero que le corresponde a la habitación", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer numero;
    @Schema(description = "Tipo de habitación, el valor es de tipo ENUM y se introduce como String", example = "SUPERIOR/DELUXE/SUITE")
    private Tipo tipo;
    @Schema(description = "Precio de la habitación especificado por noche", example = "104", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double precioPorNoche;
    @Schema(description = "Valor booleano que determina si la habitación sigue disponible", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean disponible;
    @Schema(description = "Valor que identifica el nombre del hotel al que corresponde la habitacion", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long hotel;
    private List<ReservacionDTO> reservaciones;
}
