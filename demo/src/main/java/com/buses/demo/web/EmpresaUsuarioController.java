package com.buses.demo.web;

import com.buses.demo.domain.EmpresaUsuario;
import com.buses.demo.service.EmpresaUsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa-usuario")
@Api(value = "onlinestore", description = "operations")
public class EmpresaUsuarioController {

    @Autowired
    private EmpresaUsuarioService empresaUsuarioService;

    @ApiOperation(value = "Search all EmpresaUsuario", response = EmpresaUsuario.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EmpresaUsuario>> getAllEmpresaUsuarios(){
        try {
            return new ResponseEntity(empresaUsuarioService.getAllEmpresaUsuarios(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search an EmpresaUsuario with an ID", response = EmpresaUsuario.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<EmpresaUsuario> getEmpresaUsuarioById(@PathVariable Long id){
        try {
            if (empresaUsuarioService.existEmpresaUsuarioById(id))
                return new ResponseEntity(empresaUsuarioService.getEmpresaUsuarioById(id), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Save a New EmpresaUsuario", response = EmpresaUsuario.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmpresaUsuario> saveNewEmpresaUsuario(@RequestBody EmpresaUsuario newEmpresaUsuario){
        try {
            return new ResponseEntity(empresaUsuarioService.addNewEmpresaUsuario(newEmpresaUsuario), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update an EmpresaUsuario with an ID", response = EmpresaUsuario.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<EmpresaUsuario> updateEmpresaUsuarioById(@PathVariable Long id, @RequestBody EmpresaUsuario empresaUsuario){
        try {
            if (empresaUsuarioService.existEmpresaUsuarioById(id))
                return new ResponseEntity(empresaUsuarioService.updateEmpresaUsuarioById(id, empresaUsuario), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete an EmpresaUsuario with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteEmpresaUsuarioById(@PathVariable Long id){
        try {
            if (empresaUsuarioService.existEmpresaUsuarioById(id)){
                empresaUsuarioService.deleteEmpresaUsuarioById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
