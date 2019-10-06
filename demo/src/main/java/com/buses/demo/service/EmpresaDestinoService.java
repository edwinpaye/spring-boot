package com.buses.demo.service;

import com.buses.demo.domain.EmpresaDestino;
import com.buses.demo.repository.EmpresaDestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaDestinoService {

    @Autowired
    private EmpresaDestinoRepository empresaDestinoRepository;

    public List<EmpresaDestino> getAllEmpresaDestinos(){
        return empresaDestinoRepository.findAll();
    }

    public EmpresaDestino getEmpresaDestinoById(long id){
        return empresaDestinoRepository.findById(id).get();
    }

    public EmpresaDestino addNewEmpresaDestino(EmpresaDestino newEmpresaDestino){
        return empresaDestinoRepository.save(newEmpresaDestino);
    }

    public EmpresaDestino updateEmpresaDestinoById(long id, EmpresaDestino empresaDestino){
        EmpresaDestino resp = empresaDestinoRepository.findById(id).get();
        if (empresaDestino.getBus_id()!=0)
            resp.setBus_id(empresaDestino.getBus_id());
        if (empresaDestino.getDestino_id()!=null)
            resp.setDestino_id(empresaDestino.getDestino_id());
        if (empresaDestino.getFecha_salida()!=null)
            resp.setFecha_salida(empresaDestino.getFecha_salida());
        return empresaDestinoRepository.save(resp);
    }

    public void deleteEmpreaDestinoById(long id){
        empresaDestinoRepository.deleteById(id);
    }

    public boolean existEmpresaDestinoById(long id){
        return empresaDestinoRepository.existsById(id);
    }
}
