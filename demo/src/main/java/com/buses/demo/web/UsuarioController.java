package com.buses.demo.web;

import com.buses.demo.domain.Usuario;
import com.buses.demo.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Api(value="onlinestore", description="Operations")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Search all Usuarios", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        try {
            return new ResponseEntity(usuarioService.getAllUsuarios(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search a User with an Id", response = Usuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
        try {
            if (usuarioService.existUsuarioById(id))
                return new ResponseEntity(usuarioService.getUsuarioById(id), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search for buses that match an example", response = Usuario.class)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<List<Usuario>> getUsuariosByExample(@RequestBody Usuario usuario){
        try {
            return new ResponseEntity(usuarioService.findUsuariosByExample(usuario), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Add new Usuario", response = Usuario.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Usuario> addNewUsuario(@RequestBody Usuario newUsuario){
        try {
            return new ResponseEntity(usuarioService.addNewUsuario(newUsuario), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update a Usuario with an ID", response = Usuario.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Usuario> updateUsuarioById(@PathVariable Long id, @RequestBody Usuario usuario){
        try {
            if (usuarioService.existUsuarioById(id))
                return new ResponseEntity(usuarioService.updateUsuarioById(id, usuario), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete a Usuario with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id){
        try {
            if (usuarioService.existUsuarioById(id)){
                usuarioService.deleteUsuarioById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
