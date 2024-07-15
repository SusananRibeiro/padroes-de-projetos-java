package com.padroes.entities.repositories;

import com.padroes.entities.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoClienteRepository extends JpaRepository<Cliente, Long> {
}
