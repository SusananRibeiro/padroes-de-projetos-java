package com.padroes.entities.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "pedidos")
@SQLDelete(sql = "UPDATE pedidos SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private LocalDate dataEntrega;
    @Column
    private double valorDesconto;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente clienteId;
    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco enderecoId;

    // Getters e Setters
}
