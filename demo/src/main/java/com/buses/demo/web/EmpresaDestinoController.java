package com.buses.demo.web;

import com.buses.demo.domain.EmpresaDestino;
import com.buses.demo.service.EmpresaDestinoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa-destino")
@Api(value = "oninestore", description = "operations")
public class EmpresaDestinoController {

    @Autowired
    private EmpresaDestinoService empresaDestinoService;

    @ApiOperation(value = "Search all EmpresaDestino", response = EmpresaDestino.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EmpresaDestino>> getAllEmpresaDestinos(){
        try {
            return new ResponseEntity(empresaDestinoService.getAllEmpresaDestinos(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search a EmpresaDestino with an ID", response = EmpresaDestino.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<EmpresaDestino> getEmpresaDestinoById(@PathVariable Long id){
        try {
            if (empresaDestinoService.existEmpresaDestinoById(id))
                return new ResponseEntity(empresaDestinoService.getEmpresaDestinoById(id), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Save a new EmpresaDestino", response = EmpresaDestino.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmpresaDestino> saveNewEmpresaDestino(@RequestBody EmpresaDestino newEmpresaDestino){
        try {
            return new ResponseEntity(empresaDestinoService.addNewEmpresaDestino(newEmpresaDestino), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update an EmpresaDestino with an ID", response = EmpresaDestino.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<EmpresaDestino> updateEmpresaDestinoById(@PathVariable Long id, @RequestBody EmpresaDestino empresaDestino){
        try {
            if (empresaDestinoService.existEmpresaDestinoById(id))
                return new ResponseEntity(empresaDestinoService.updateEmpresaDestinoById(id, empresaDestino), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete an EmpresaDestino with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteEmpresaDestinoById(@PathVariable Long id){
        try {
            if (empresaDestinoService.existEmpresaDestinoById(id)){
                empresaDestinoService.deleteEmpreaDestinoById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
