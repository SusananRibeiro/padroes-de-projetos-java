package com.padroes.entities.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;
@Data
@Entity(name = "pedidos_itens")
@SQLDelete(sql = "UPDATE pedidos_itens SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class PedidoItens {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false)
    private double valorUnitario;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produtoId;
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedidoId;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Getters e Setters
}
