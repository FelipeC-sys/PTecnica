package com.prueba.tecnica.dto;

import com.prueba.tecnica.model.EstadoTarea;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstadoDTO {

    @NotNull(message = "Debe enviar un estado")
    private EstadoTarea estado;
}