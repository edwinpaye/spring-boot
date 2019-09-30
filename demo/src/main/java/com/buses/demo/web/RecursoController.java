package com.buses.demo.web;

import com.buses.demo.domain.Recurso;
import com.buses.demo.service.RecursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recursos")
@Api(value="onlinestore", description="Operations")
public class RecursoController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(RecursoController.class);

    @Autowired
    private RecursoService recursoService;

    //    @ResponseBody()
    @ApiOperation(value = "Search all recursos",response = Recurso.class)
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Recurso> getAllRecursos(){
        return recursoService.getAllRecursos();
    }

    @ApiOperation(value = "Search a recurso with an ID", response = Recurso.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Recurso getRecursoById(@PathVariable long id){
        logger.info("the value of recurso id is " + id);
        Optional<Recurso> resp = recursoService.getRecursoById(id);
        if(resp.isPresent())
            return resp.get();
        return null;
    }

    @ApiOperation(value = "Update a recurso", response = Recurso.class)
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<Recurso> updateRecurso(@RequestBody Recurso recurso){
        logger.info("the value of recurso id is " + recurso.getId());
//        recursoService.updateRecurso(recurso);
        return new ResponseEntity(recursoService.updateRecurso(recurso), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a recurso", response = Recurso.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Recurso> createRecurso(@RequestBody Recurso recurso){
        logger.info("test create----->" + recurso.getId());
        return new ResponseEntity(recursoService.addRecurso(recurso), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a recurso")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteRecurso(@RequestBody Recurso recurso){
        logger.info("test delete----->" + recurso.getId());
        try {
            recursoService.deleteRecurso(recurso);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.GONE);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete a recurso with an ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity deleteRecursoById(@PathVariable long id){
        logger.info("test delete----->" + id);
        try{
            recursoService.deleteRecursoById(id);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.GONE);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
