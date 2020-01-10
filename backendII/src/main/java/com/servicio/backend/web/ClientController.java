package com.servicio.backend.web;

import com.servicio.backend.entity.Client;
import com.servicio.backend.exception.RecordNotFoundException;
import com.servicio.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getClientById(id).get());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<List<Client>> getClientsByExample(@RequestBody Client client){
        return ResponseEntity.ok(clientService.findClientByExample(client));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Client> addNewClient(@Valid @RequestBody Client newClient){
        return ResponseEntity.ok(clientService.addClient(newClient));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public ResponseEntity<Client> updateClientById(@PathVariable Long id, @RequestBody Client client){
        Client resp = clientService.updateClientById(id, client);
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id){
        if (!clientService.deleteClientById(id))
            throw new RecordNotFoundException("Could not find client: " + id);
        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
    }
}
