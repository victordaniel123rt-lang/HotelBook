package com.vdgarcia.HotelBook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservacion")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Reservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private LocalDate fechaReserva;
    private Boolean estado;
    private Double total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;


}
