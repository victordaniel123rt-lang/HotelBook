package com.vdgarcia.HotelBook.controller;

import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.dto.HotelDTO;
import com.vdgarcia.HotelBook.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
@Tag(name = "Hoteles", description = "Controlador para la gestión y creación de hoteles")
public class HotelController {
    private final HotelService service;

    @GetMapping
    @Operation(summary = "Listar todas los hoteles", description = "Muestra todos los registros hasta el momento de los hoteles que se han realizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista desplegada con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<List<HotelDTO>> buscarTodas(){
        return ResponseEntity.ok(service.buscarTodos());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Encontrar un hotel", description = "Busca un hotel en especifica por medio de un identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado y desplegado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<HotelDTO> buscarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo hotel", description = "Registra un hotel en el sistema. No se debe enviar el ID en el cuerpo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<HotelDTO> crear(@RequestBody HotelDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un hotel", description = "Hace la actualización correspondiente de un hotel. Es necesario incluir el id en el cuerpo del URI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<HotelDTO> actualizar(@PathVariable Long id, @RequestBody HotelDTO dto){
        return ResponseEntity.ok(service.actualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un hotel", description = "Elimina un hotel creado con anterioridad. Es necesario el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200/2004", description = "Elemento eliminado con exito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o inconsistentes")
    })
    public ResponseEntity<HotelDTO> eliminar (@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }

}
