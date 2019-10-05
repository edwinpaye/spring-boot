package com.buses.demo.service;

import com.buses.demo.domain.Destino;
import com.buses.demo.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepo;

    public List<Destino> getAllDestinos(){
        return destinoRepo.findAll();
    }

    public Destino getDetinoById(long id){
        return destinoRepo.findById(id).get();
    }

    public Destino addNewDestino(Destino newDestino){
        newDestino.setCreation(new Date());
        return destinoRepo.save(newDestino);
    }

    public Destino updateDestinoById(long id, Destino destino){
        Destino resp = destinoRepo.findById(id).get();
        if (destino.getNombre()!=null)
            resp.setNombre(destino.getNombre());
        if (destino.getUbicacion()!=null)
            resp.setUbicacion(destino.getUbicacion());
        return destinoRepo.save(resp);
    }

    public void deleteDestinoById(long id){
        destinoRepo.deleteById(id);
    }

    public boolean existDestinoById(long id){
        return destinoRepo.existsById(id);
    }
}
