package com.buses.demo.web;

import com.buses.demo.domain.Bus;
import com.buses.demo.service.BusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buses")
@Api(value="onlinestore", description="Operations")
public class BusController {

    @Autowired
    private BusService busService;

    @ApiOperation(value = "Search all buses", response = Bus.class)
    @RequestMapping(method = RequestMethod.GET)
    public List<Bus> getAllBuses(){
        return busService.getAllBuses();
    }

    @ApiOperation(value = "Search a bus with an ID", response = Bus.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable long id){
        Optional<Bus> resp = busService.getBusById(id);
        if (resp.isPresent())
            return new ResponseEntity(resp.get(), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
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
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable long id, @RequestBody Bus bus){
        return new ResponseEntity(busService.updateBusById(id, bus), HttpStatus.OK);
    }
//    public
}
