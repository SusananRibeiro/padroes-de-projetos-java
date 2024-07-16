package com.padroes.entities.repositories;

import com.padroes.entities.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRelatorioRepository extends JpaRepository<Cliente, Long> {
    public boolean existsByCpf(String cpf);

}

