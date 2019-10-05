package com.buses.demo.service;

import com.buses.demo.domain.Empresa;
import com.buses.demo.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepo;

    public List<Empresa> getAllEmpresas(){
        return empresaRepo.findAll();
    }

    public Empresa getEmpresaById(long id){
        return empresaRepo.findById(id).get();
    }

    public Empresa addNewEmpresa(Empresa newEmpresa){
        newEmpresa.setCreation(new Date());
        return empresaRepo.save(newEmpresa);
    }

    //Refactorizar el metodo, arreglar las relaciones
    public Empresa updateEmpresaById(long id, Empresa empresa){
        Empresa resp = empresaRepo.findById(id).get();
        if (empresa.getNombre()!=null)
            resp.setNombre(empresa.getNombre());
        if (empresa.getTelefono()!=0)
            resp.setTelefono(empresa.getTelefono());
        return empresaRepo.save(resp);
    }

    public void deleteEmpresaById(long id){
        empresaRepo.deleteById(id);
    }

    public List<Empresa> findEmpresasByExample(Empresa empresa){
        return empresaRepo.findAll(Example.of(empresa));
    }

    public boolean existEmpresaById(long id){
        return empresaRepo.existsById(id);
    }
}
