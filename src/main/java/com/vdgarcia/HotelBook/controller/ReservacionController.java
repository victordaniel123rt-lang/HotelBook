package com.vdgarcia.HotelBook.controller;


import com.vdgarcia.HotelBook.dto.ReservacionDTO;
import com.vdgarcia.HotelBook.service.ReservacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservaciones")
@RequiredArgsConstructor
@Tag(name = "Reservaciones", description = "Controlador para la gestión de reservas de habitaciones")
public class ReservacionController {
    private final ReservacionService service;

    @GetMapping
    @Operation(summary = "Listar todas las reservaciones", description = "Muestra todos los registros hasta el momento de las reservaciones que se han realizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista desplegada con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<List<ReservacionDTO>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Encontrar una reservacion por id", description = "Muestra una reservacion en especifica correspondiente a un id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<ReservacionDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva reservación", description = "Registra una reserva en el sistema. No se debe enviar el ID en el cuerpo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservación creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<ReservacionDTO> crear(@RequestBody ReservacionDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservacionDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }


}
