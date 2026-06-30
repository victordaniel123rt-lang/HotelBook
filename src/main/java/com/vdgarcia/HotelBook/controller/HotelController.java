package com.vdgarcia.HotelBook.controller;

import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.dto.HotelDTO;
import com.vdgarcia.HotelBook.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService service;

    @GetMapping
    public ResponseEntity<List<HotelDTO>> buscarTodas(){
        return ResponseEntity.ok(service.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> buscarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<HotelDTO> crear(@RequestBody HotelDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> actualizar(@PathVariable Long id, @RequestBody HotelDTO dto){
        return ResponseEntity.ok(service.actualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HotelDTO> eliminar (@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }

}
