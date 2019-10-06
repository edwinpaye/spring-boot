package com.buses.demo.web;

import com.buses.demo.domain.EmpresaBus;
import com.buses.demo.service.EmpresaBusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa-bus")
@Api(value = "onlinestore", description = "operations")
public class EmpresaBusController {

    @Autowired
    private EmpresaBusService empresaBusService;

    @ApiOperation(value = "Search all buses", response = EmpresaBus.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EmpresaBus>> getAllEmpresaBuses(){
        try {
            return new ResponseEntity(empresaBusService.getAllEmpresaBuses(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search a EmpresaBus with an ID", response = EmpresaBus.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<EmpresaBus> getEmpresaBusById(@PathVariable Long id){
        try {
            if (empresaBusService.existEmpresaBusById(id))
                return new ResponseEntity(empresaBusService.getEmpresaBusById(id), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Save a new EmpresaBus", response = EmpresaBus.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmpresaBus> saveNewEmpresaBus(@RequestBody EmpresaBus newEmpresaBus){
        try {
            return new ResponseEntity(empresaBusService.addNewEmpresaBus(newEmpresaBus), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update a EmpresaBus with an ID", response = EmpresaBus.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<EmpresaBus> updateEmpresaBusById(@PathVariable Long id, @RequestBody EmpresaBus empresaBus){
        try {
            if (empresaBusService.existEmpresaBusById(id))
                return new ResponseEntity(empresaBusService.updateEmpresaBusById(id, empresaBus), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete a EmpresaBus with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteEmpresaBusById(@PathVariable Long id){
        try {
            if (empresaBusService.existEmpresaBusById(id)){
                empresaBusService.deleteEmpresaBusById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
