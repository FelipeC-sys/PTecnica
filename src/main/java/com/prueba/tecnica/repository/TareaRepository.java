package com.prueba.tecnica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.tecnica.model.EstadoTarea;
import com.prueba.tecnica.model.PrioridadTarea;
import com.prueba.tecnica.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByEstado(EstadoTarea estado);

    List<Tarea> findByPrioridad(PrioridadTarea prioridad);

    List<Tarea> findByTituloContainingIgnoreCase(String titulo);

}