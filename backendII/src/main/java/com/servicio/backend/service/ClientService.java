package com.servicio.backend.service;

import com.servicio.backend.entity.Client;
import com.servicio.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepo;

    public List<Client> getAllBuses(){
        return clientRepo.findAll();
    }

    public Optional<Client> getClientById(long id){
        return clientRepo.findById(id);
    }

    public Client addClient(Client newClient){
        newClient.setCreate(new Date());
        return clientRepo.save(newClient);
    }

    public Client updateClientById(Long id, Client bus){
        Client resp = clientRepo.findById(id).get();
//        resp.setId(id);
//        if (bus.getDestino() != null)
//            resp.setDestino(bus.getDestino());
//        if (bus.getMatricula() != null)
//            resp.setMatricula(bus.getMatricula());
        return clientRepo.save(resp);
    }

    public void deleteBusById(Long id){
        clientRepo.deleteById(id);
    }

    public List<Client> findClientByExample(Client client){
        return clientRepo.findAll(Example.of(client));
    }

    public boolean existClientById(long id){
        return clientRepo.existsById(id);
    }

    public boolean existClientByExample(Client exampleClient){
        return clientRepo.exists(Example.of(exampleClient));
    }
}
