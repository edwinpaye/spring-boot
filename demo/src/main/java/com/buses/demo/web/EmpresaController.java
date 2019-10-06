package com.buses.demo.web;

import com.buses.demo.domain.Empresa;
import com.buses.demo.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@Api(value="onlinestore", description="Operations")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @ApiOperation(value = "Search all Empresas", response = Empresa.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Empresa>> getAllEmresas(){
        try {
            return new ResponseEntity(empresaService.getAllEmpresas(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search a Empresa with an ID", response = Empresa.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id){
        try {
            if (empresaService.existEmpresaById(id))
                return new ResponseEntity(empresaService.getEmpresaById(id), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search for buses that match an example", response = Empresa.class)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<List<Empresa>> getEmpresasByExample(@RequestBody Empresa empresa){
        try {
            return new ResponseEntity<>(empresaService.findEmpresasByExample(empresa), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Create a new Empresa", response = Empresa.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Empresa> addNewEmpresa(@RequestBody Empresa newEmpresa){
        try {
            return new ResponseEntity(empresaService.addNewEmpresa(newEmpresa), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update a Empresa with an ID", response = Empresa.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Empresa> updateEmpresaById(@PathVariable Long id, @RequestBody Empresa empresa){
        try {
            if (empresaService.existEmpresaById(id))
                return new ResponseEntity(empresaService.updateEmpresaById(id, empresa), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete a Empres with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteEmpresaById(@PathVariable Long id){
        try {
            if (empresaService.existEmpresaById(id)){
                empresaService.deleteEmpresaById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
