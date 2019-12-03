package com.buses.demo.service;

import com.buses.demo.domain.Usuario;
import com.buses.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepo.findAll();
    }

    public Usuario getUsuarioById(long id) {
        return usuarioRepo.findById(id).get();
    }

    public Usuario addNewUsuario(Usuario newUsuario){
        newUsuario.setCreation(new Date());
        return usuarioRepo.save(newUsuario);
    }

    public Usuario updateUsuarioById(long id, Usuario usuario){
        Usuario resp = usuarioRepo.findById(id).get();
        if (usuario.getApellido()!=null)
            resp.setApellido(usuario.getApellido());
        if (usuario.getCreation()!=null)
            resp.setCreation(usuario.getCreation());
        if (usuario.getEmail()!=null)
            resp.setEmail(usuario.getEmail());
        if (usuario.getNombre()!=null)
            resp.setNombre(usuario.getNombre());
        if (usuario.getPassword()!=null)
            resp.setPassword(usuario.getPassword());
        if (usuario.getTelefono()!=0)
            resp.setTelefono(usuario.getTelefono());
        return usuarioRepo.save(resp);
    }

    public void deleteUsuarioById(long id){
        usuarioRepo.deleteById(id);
    }

    public boolean existUsuarioById(long id){
        return usuarioRepo.existsById(id);
    }

    public long countUsuarios(){
        return usuarioRepo.count();
    }

    public List<Usuario> findUsuariosByExample(Usuario usuario){
        return usuarioRepo.findAll(Example.of(usuario));
    }

    public List<Usuario> findUsuariosByName(String name){
        return usuarioRepo.findByNombreContainingIgnoreCase(name);
    }
}
