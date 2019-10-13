package com.buses.demo.service;

import com.buses.demo.domain.EmpresaBus;
import com.buses.demo.repository.EmpresaBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmpresaBusService {

    @Autowired
    private EmpresaBusRepository empresaBusRepository;

    public List<EmpresaBus> getAllEmpresaBuses(){
        return empresaBusRepository.findAll();
    }

    public EmpresaBus getEmpresaBusById(long id){
        return empresaBusRepository.findById(id).get();
    }

    public EmpresaBus addNewEmpresaBus(EmpresaBus newEmpresaBus){
        newEmpresaBus.setCreation(new Date());
        return empresaBusRepository.save(newEmpresaBus);
    }

    public EmpresaBus updateEmpresaBusById(long id, EmpresaBus empresaBus){
        EmpresaBus resp = empresaBusRepository.findById(id).get();
        if (empresaBus.getDestino()!=null)
            resp.setDestino(empresaBus.getDestino());
        if (empresaBus.getBus_id()!=null)
            resp.setBus_id(empresaBus.getBus_id());
        resp.setActualizacion(new Date());
        return empresaBusRepository.save(resp);
    }

    public void deleteEmpresaBusById(long id){
        empresaBusRepository.deleteById(id);
    }

    public boolean existEmpresaBusById(long id){
        return empresaBusRepository.existsById(id);
    }
}
