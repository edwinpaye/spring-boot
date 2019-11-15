package com.buses.demo.web;

import com.buses.demo.domain.Bus;
import com.buses.demo.service.BusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/buses")
@Api(value="onlinestore", description="Operations")
public class BusController {

    @Autowired
    private BusService busService;

    @ApiOperation(value = "Search all buses", response = Bus.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Bus>> getAllBuses(){
        try {
            return new ResponseEntity<>(busService.getAllBuses(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search a bus with an ID", response = Bus.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable long id){
        try {
            if (busService.existBusById(id))
                return new ResponseEntity(busService.getBusById(id).get(), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Search for buses that match an example", response = Bus.class)
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<List<Bus>> getBusByExample(@RequestBody Bus bus){
        try {
            return new ResponseEntity(busService.findBusesByExample(bus), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Create a bus", response = Bus.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus){
        try {
            return new ResponseEntity(busService.addBus(bus), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Update a bus", response = Bus.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bus> updateBus(@PathVariable long id, @RequestBody Bus bus){
        try {
            if (busService.existBusById(id))
                return new ResponseEntity(busService.updateBusById(id, bus), HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete a Bus with an Id")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteBusById(@PathVariable long id){
        try {
            if (busService.existBusById(id)){
                busService.deleteBusById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
