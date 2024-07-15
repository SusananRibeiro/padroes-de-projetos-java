package com.padroes.entities.repositories;

import com.padroes.entities.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoEnderecoRepository extends JpaRepository<Endereco, Long> {
}

