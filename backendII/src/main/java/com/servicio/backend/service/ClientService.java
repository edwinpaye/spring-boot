package com.servicio.backend.service;

import com.servicio.backend.entity.Client;
import com.servicio.backend.exception.RecordNotFoundException;
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

    public List<Client> getAllClients(){
        return clientRepo.findAll();
    }

    public Optional<Client> getClientById(long id){
        return clientRepo.findById(id);
    }

    public Client addClient(Client newClient){
        newClient.setCreate(new Date());
        return clientRepo.save(newClient);
    }

    public Client updateClientById(Long id_client, Client client){
        Client resp = clientRepo.findById(id_client).get();
        resp.setId_client(id_client);
        if (client.getEmail() != null)
            resp.setEmail(client.getEmail());
        if (client.getLastName() != null)
            resp.setLastName(client.getLastName());
        if (client.getName() != null)
            resp.setName(client.getName());
        if (client.getPhone() != null)
            resp.setPhone(client.getPhone());
        if (client.getPicture() != null)
            resp.setPicture(client.getPicture());
        return clientRepo.save(resp);
    }

    public boolean deleteClientById(long id) throws RecordNotFoundException {
        if (!clientRepo.existsById(id))
            throw new RecordNotFoundException("Could not find client: " + id);
        clientRepo.deleteById(id);
        return !clientRepo.existsById(id);
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
