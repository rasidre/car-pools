package com.car.seller.carpool.controllers;

import com.car.seller.carpool.models.Client;
import com.car.seller.carpool.repositories.ClientRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private static Client client;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public @ResponseBody Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getClientById(@PathVariable int id) {
        client = clientRepository.findById(id);

        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity addClient(@RequestBody Client newClient) {
        if (newClient.isValid()) {
            clientRepository.save(newClient);
            return ResponseEntity.ok("Your client was added!");
        }
        return ResponseEntity.unprocessableEntity().body("Your request body is not correct!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable int id) {
        client = clientRepository.findById(id);
        if (client != null) {
            clientRepository.deleteById(id);
            return ResponseEntity.ok("The client was successfully deleted!");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable int id, @RequestBody Client newClient) {
        client = clientRepository.findById(id);
        String newClientFirstName = newClient.getFirstName();
        String newClientLastName = newClient.getLastName();
        String newClientEmail = newClient.getEmail();
        if (client != null) {
            if (newClientEmail != null) {
                client.setEmail(newClientEmail);
            }
            if (newClientFirstName != null) {
                client.setFirstName(newClientFirstName);
            }
            if (newClientLastName != null) {
                client.setLastName(newClientLastName);
            }
            clientRepository.save(client);
            return ResponseEntity.ok("The client was successfully udpated!");
        } else if (newClient.isValid()) {
            clientRepository.save(newClient);
            return ResponseEntity.ok("The client was successfully created!");
        }
        return ResponseEntity.notFound().build();
    }
}
