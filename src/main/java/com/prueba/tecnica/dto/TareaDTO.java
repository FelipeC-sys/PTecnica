package com.prueba.tecnica.dto;

import com.prueba.tecnica.model.EstadoTarea;
import com.prueba.tecnica.model.PrioridadTarea;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TareaDTO {

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 150)
    private String titulo;

    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTarea prioridad;

    private EstadoTarea estado;

}