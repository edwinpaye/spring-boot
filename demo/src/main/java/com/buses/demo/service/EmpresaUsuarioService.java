package com.buses.demo.service;

import com.buses.demo.domain.EmpresaUsuario;
import com.buses.demo.repository.EmpresaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmpresaUsuarioService {

    @Autowired
    private EmpresaUsuarioRepository empresaUsuarioRepository;

    public List<EmpresaUsuario> getAllEmpresaUsuarios(){
        return empresaUsuarioRepository.findAll();
    }

    public EmpresaUsuario getEmpresaUsuarioById(long id){
        return empresaUsuarioRepository.findById(id).get();
    }

    public EmpresaUsuario addNewEmpresaUsuario(EmpresaUsuario newEmpresaUsuario){
        newEmpresaUsuario.setCreacion(new Date());
        return empresaUsuarioRepository.save(newEmpresaUsuario);
    }

    public EmpresaUsuario updateEmpresaUsuarioById(long id, EmpresaUsuario empresaUsuario){
        EmpresaUsuario resp = empresaUsuarioRepository.findById(id).get();
        if (empresaUsuario.getEmpresa_id()!=null)
            resp.setEmpresa_id(empresaUsuario.getEmpresa_id());
        if (empresaUsuario.getPermiso_usuario()!=null)
            resp.setPermiso_usuario(empresaUsuario.getPermiso_usuario());
        return empresaUsuarioRepository.save(resp);
    }

    public void deleteEmpresaUsuarioById(long id){
        empresaUsuarioRepository.deleteById(id);
    }

    public boolean existEmpresaUsuarioById(long id){
        return empresaUsuarioRepository.existsById(id);
    }
}
