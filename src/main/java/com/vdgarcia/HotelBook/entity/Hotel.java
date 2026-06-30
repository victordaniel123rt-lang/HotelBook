package com.vdgarcia.HotelBook.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="hotel")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String categoria;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;
}
