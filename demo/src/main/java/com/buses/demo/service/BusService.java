package com.buses.demo.service;

import com.buses.demo.domain.Bus;
import com.buses.demo.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepo;

    public List<Bus> getAllBuses(){
        return busRepo.findAll();
    }

    public Optional<Bus> getBusById(long id){
        return busRepo.findById(id);
    }

    public Bus addBus(Bus newBus){
        newBus.setCreation(new Date());
        return busRepo.save(newBus);
    }

    public Bus updateBusById(Long id, Bus bus){
        if (busRepo.existsById(id)){
            bus.setId(id);
            return busRepo.save(bus);
        }
        return null;
    }

    public boolean deleteBus(Bus bus){
        if (busRepo.existsById(bus.getId())){
            busRepo.delete(bus);
            return true;
        }
        return false;
    }

    public boolean deleteBusById(Long id){
        if (busRepo.existsById(id)){
            busRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Bus> findBusesByExample(Bus bus){
        return busRepo.findAll(Example.of(bus));
    }
}
