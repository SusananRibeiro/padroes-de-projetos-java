package com.padroes.entities.repositories;

import com.padroes.entities.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ClienteEnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query("SELECT end FROM enderecos end WHERE end.cliente.id = :id")
    List<Endereco> carregarEnderecoByClienteId(@Param("id") Long id);
}
