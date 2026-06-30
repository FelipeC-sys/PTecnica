package com.prueba.tecnica.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prueba.tecnica.dto.ApiResponse;
import com.prueba.tecnica.dto.EstadoDTO;
import com.prueba.tecnica.dto.TareaDTO;
import com.prueba.tecnica.model.EstadoTarea;
import com.prueba.tecnica.model.PrioridadTarea;
import com.prueba.tecnica.model.Tarea;
import com.prueba.tecnica.service.TareaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    // Listar todas las tareas
    @GetMapping
    public List<Tarea> listarTodas() {
        return service.listarTodas();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public Tarea buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Crear tarea
    @PostMapping
    public ResponseEntity<ApiResponse> crear(@Valid @RequestBody TareaDTO dto) {

        Tarea tarea = service.crear(dto);

        ApiResponse respuesta = new ApiResponse(
                true,
                "Tarea creada correctamente",
                tarea);

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    // Actualizar tarea
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TareaDTO dto) {

        Tarea tarea = service.actualizar(id, dto);

        ApiResponse respuesta = new ApiResponse(
                true,
                "Tarea actualizada correctamente",
                tarea);

        return ResponseEntity.ok(respuesta);
    }

    // Eliminar tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        ApiResponse respuesta = new ApiResponse(
                true,
                "Tarea eliminada correctamente",
                null);

        return ResponseEntity.ok(respuesta);
    }

    // Cambiar estado
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse> cambiarEstado(
            @PathVariable Long id,
            @RequestBody EstadoDTO dto) {

        Tarea tarea = service.cambiarEstado(id, dto);

        ApiResponse respuesta = new ApiResponse(
                true,
                "Estado actualizado correctamente",
                tarea);

        return ResponseEntity.ok(respuesta);
    }

    // Filtrar por estado
    @GetMapping("/estado/{estado}")
    public List<Tarea> buscarPorEstado(@PathVariable EstadoTarea estado) {
        return service.buscarPorEstado(estado);
    }

    // Filtrar por prioridad
    @GetMapping("/prioridad/{prioridad}")
    public List<Tarea> buscarPorPrioridad(@PathVariable PrioridadTarea prioridad) {
        return service.buscarPorPrioridad(prioridad);
    }

    // Buscar por título
    @GetMapping("/buscar")
    public List<Tarea> buscarPorTitulo(@RequestParam String titulo) {
        return service.buscarPorTitulo(titulo);
    }

}