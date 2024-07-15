package com.padroes.entities.repositories;
import com.padroes.entities.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItensPedidoRepository extends JpaRepository<Pedido, Long> {

}
