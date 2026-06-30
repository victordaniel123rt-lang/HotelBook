package com.vdgarcia.HotelBook.controller;

import com.vdgarcia.HotelBook.dto.ClienteDTO;
import com.vdgarcia.HotelBook.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodos(){
        return ResponseEntity.ok(clienteService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO dto){
        return ResponseEntity.ok(clienteService.crear(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable("id") Long id, @RequestBody ClienteDTO dto){
        return ResponseEntity.ok(clienteService.actualizar(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.eliminar(id));
    }
}
