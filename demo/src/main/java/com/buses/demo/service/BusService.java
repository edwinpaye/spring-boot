package com.buses.demo.service;

import com.buses.demo.domain.Bus;
import com.buses.demo.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
        Bus resp = busRepo.findById(id).get();
        resp.setId(id);
        if (bus.getDestino() != null)
            resp.setDestino(bus.getDestino());
        if (bus.getMatricula() != null)
            resp.setMatricula(bus.getMatricula());
        return busRepo.save(resp);
    }

    public void deleteBusById(Long id){
        busRepo.deleteById(id);
    }

    public List<Bus> findBusesByExample(Bus bus){
        return busRepo.findAll(Example.of(bus));
    }

    public boolean existBusById(long id){
        return busRepo.existsById(id);
    }

    public boolean existBusByExample(Bus exampleBus){
        return busRepo.exists(Example.of(exampleBus));
    }
}
