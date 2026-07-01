package com.vdgarcia.HotelBook.controller;


import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.service.HabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
@Tag(name = "Habitaciones", description = "Controlador para la gestión de reservas de habitaciones")
public class HabitacionController {
    private final HabitacionService service;

    @GetMapping()
    @Operation(summary = "Listar todas las Habitaciones", description = "Muestra todos los registros hasta el momento de las Habitaciones que se han realizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista desplegada con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<List<HabitacionDTO>> buscarTodas(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Encontrar una habitacion", description = "Busca una habitación en especifica por medio de un identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado y desplegado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<HabitacionDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva habitación", description = "Registra una habitación en el sistema. No se debe enviar el ID en el cuerpo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habitación creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<HabitacionDTO> crear(@RequestBody HabitacionDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una habitación", description = "Hace la actualización correspondiente de una habitación. Es necesario incluir el id en el cuerpo del URI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<HabitacionDTO> actualizar(@PathVariable("id") Long id, @RequestBody HabitacionDTO dto){
        return ResponseEntity.ok(service.actualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una habitación", description = "Elimina una habitación creado con anterioridad. Es necesario el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200/2004", description = "Elemento eliminado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<HabitacionDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }


}
