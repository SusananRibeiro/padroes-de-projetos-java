package com.padroes.entities.repositories;
import com.padroes.entities.models.PedidoItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItensRepository extends JpaRepository<PedidoItens, Long> {
}
