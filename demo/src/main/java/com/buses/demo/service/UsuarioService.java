package com.buses.demo.service;

import com.buses.demo.domain.Usuario;
import com.buses.demo.exception.RecordNotFoundException;
import com.buses.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepo.findAll();
    }

    public Usuario getUsuarioById(long id) throws RecordNotFoundException {
        return usuarioRepo.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Could not find usuario: " + id));
    }

    public Usuario addNewUsuario(Usuario newUsuario){
        newUsuario.setCreation(new Date());
        return usuarioRepo.save(newUsuario);
    }

    public Usuario updateUsuarioById(long id, Usuario usuario) throws RecordNotFoundException {
        Usuario updated = usuarioRepo.findById(id).map((resp)->{
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
            return resp;
        }).orElseThrow(() -> new RecordNotFoundException("Could not find usuario: " + id));
        return usuarioRepo.save(updated);
    }

    public boolean deleteUsuarioById(long id) throws RecordNotFoundException{
        if (!usuarioRepo.existsById(id))
            throw new RecordNotFoundException("Could not find usuario: " + id);
        usuarioRepo.deleteById(id);
        return !usuarioRepo.existsById(id);
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

    public Page<Usuario> getAllUsuarios(Pageable newPageable){
        return usuarioRepo.findAll(newPageable);
    }
}
