package com.prueba.tecnica.service;

import java.util.List;

import com.prueba.tecnica.dto.EstadoDTO;
import com.prueba.tecnica.dto.TareaDTO;
import com.prueba.tecnica.model.EstadoTarea;
import com.prueba.tecnica.model.PrioridadTarea;
import com.prueba.tecnica.model.Tarea;

public interface TareaService {

    List<Tarea> listarTodas();

    Tarea buscarPorId(Long id);

    Tarea crear(TareaDTO tareaDTO);

    Tarea actualizar(Long id, TareaDTO tareaDTO);

    void eliminar(Long id);

    Tarea cambiarEstado(Long id, EstadoDTO estadoDTO);

    List<Tarea> buscarPorEstado(EstadoTarea estado);

    List<Tarea> buscarPorPrioridad(PrioridadTarea prioridad);

    List<Tarea> buscarPorTitulo(String titulo);

}