package com.car.seller.carpool.repositories;

import com.car.seller.carpool.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findById(int id);

}
