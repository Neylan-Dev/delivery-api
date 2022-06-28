package com.algaworks.algalog.repository;

import com.algaworks.algalog.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
