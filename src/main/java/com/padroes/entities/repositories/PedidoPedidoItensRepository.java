package com.padroes.entities.repositories;

import com.padroes.entities.models.PedidoItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoPedidoItensRepository extends JpaRepository<PedidoItens, Long> {
    @Query("select pi from pedidos_itens pi where pi.pedidoId.id = :id")
    List<PedidoItens> carregarPedidosItensByPedidoId(@Param("id") Long id);
}
