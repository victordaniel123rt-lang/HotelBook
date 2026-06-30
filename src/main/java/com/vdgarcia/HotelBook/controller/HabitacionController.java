package com.vdgarcia.HotelBook.controller;


import com.vdgarcia.HotelBook.dto.HabitacionDTO;
import com.vdgarcia.HotelBook.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {
    private final HabitacionService service;

    @GetMapping()
    public ResponseEntity<List<HabitacionDTO>> buscarTodas(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<HabitacionDTO> crear(@RequestBody HabitacionDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<HabitacionDTO> actualizar(@PathVariable("id") Long id, @RequestBody HabitacionDTO dto){
        return ResponseEntity.ok(service.actualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HabitacionDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }


}
