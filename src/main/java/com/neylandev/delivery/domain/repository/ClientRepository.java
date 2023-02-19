package com.neylandev.delivery.domain.repository;

import com.neylandev.delivery.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByName(String name);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByTelephone(String telephone);
}
