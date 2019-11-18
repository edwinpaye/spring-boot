package com.buses.demo.web;

import com.buses.demo.domain.Destino;
import com.buses.demo.service.DestinoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/destino")
@Api(value="onlinestore", description="Operations")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @ApiOperation(value = "Search all Destinos", response = Destino.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Destino>> getAllDestinos(){
        try {
            return new ResponseEntity(destinoService.getAllDestinos(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search a Destino with an Id", response = Destino.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Destino> getDestinoById(@PathVariable Long id){
        try {
            if (destinoService.existDestinoById(id))
                return new ResponseEntity(destinoService.getDetinoById(id), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Save a Destino", response = Destino.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Destino> saveNewDestino(@RequestBody Destino newDestino){
        try {
            return new ResponseEntity(destinoService.addNewDestino(newDestino), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update a Destino with an ID", response = Destino.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Destino> updateDestinoById(@PathVariable Long id, @RequestBody Destino destino){
        try {
            if (destinoService.existDestinoById(id))
                return new ResponseEntity(destinoService.updateDestinoById(id, destino), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete a Destino with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteDestinoByID(@PathVariable Long id){
        try {
            if (destinoService.existDestinoById(id)){
                destinoService.deleteDestinoById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
