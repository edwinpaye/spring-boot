package com.buses.demo.service;

import com.buses.demo.domain.Recurso;
import com.buses.demo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepo;

    public Iterable<Recurso> getAllRecursos(){
        return recursoRepo.findAll();
    }

    public Optional<Recurso> getRecursoById(Long id){
        return recursoRepo.findById(id);
    }
    public Recurso addRecurso(Recurso newRecurso){
        return recursoRepo.save(newRecurso);
    }

    public void deleteRecurso(Recurso recurso){
        recursoRepo.delete(recurso);
    }

    public void deleteRecursoById(Long id){
        recursoRepo.deleteById(id);
    }

    public Recurso updateRecurso(Recurso recurso){
        return recursoRepo.save(recurso);
    }
}
