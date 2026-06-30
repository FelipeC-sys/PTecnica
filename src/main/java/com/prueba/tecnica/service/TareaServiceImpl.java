package com.prueba.tecnica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.tecnica.dto.EstadoDTO;
import com.prueba.tecnica.dto.TareaDTO;
import com.prueba.tecnica.exception.BusinessException;
import com.prueba.tecnica.exception.ResourceNotFoundException;
import com.prueba.tecnica.model.EstadoTarea;
import com.prueba.tecnica.model.PrioridadTarea;
import com.prueba.tecnica.model.Tarea;
import com.prueba.tecnica.repository.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService {

    private final TareaRepository repository;

    public TareaServiceImpl(TareaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tarea> listarTodas() {
        return repository.findAll();
    }

    @Override
    public Tarea buscarPorId(Long id) {

        Optional<Tarea> tarea = repository.findById(id);

        if (tarea.isEmpty()) {
            throw new ResourceNotFoundException("Tarea no encontrada");
        }

        return tarea.get();
    }

    @Override
    public Tarea crear(TareaDTO dto) {

        Tarea tarea = new Tarea();

        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setPrioridad(dto.getPrioridad());

        return repository.save(tarea);
    }

    @Override
    public Tarea actualizar(Long id, TareaDTO dto) {

        Tarea tarea = buscarPorId(id);

        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setPrioridad(dto.getPrioridad());

        return repository.save(tarea);
    }

    @Override
    public void eliminar(Long id) {

        Tarea tarea = buscarPorId(id);

        repository.delete(tarea);

    }

    @Override
    public Tarea cambiarEstado(Long id, EstadoDTO dto) {

        Tarea tarea = buscarPorId(id);

        if (tarea.getEstado() == EstadoTarea.CANCELADA) {
            throw new BusinessException("La tarea cancelada no puede cambiar de estado.");
        }

        if (tarea.getEstado() == EstadoTarea.COMPLETADA &&
                (dto.getEstado() == EstadoTarea.PENDIENTE ||
                        dto.getEstado() == EstadoTarea.EN_PROGRESO)) {

            throw new BusinessException("La tarea completada no puede volver a ese estado.");
        }

        tarea.setEstado(dto.getEstado());

        return repository.save(tarea);
    }

    @Override
    public List<Tarea> buscarPorEstado(EstadoTarea estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public List<Tarea> buscarPorPrioridad(PrioridadTarea prioridad) {
        return repository.findByPrioridad(prioridad);
    }

    @Override
    public List<Tarea> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

}