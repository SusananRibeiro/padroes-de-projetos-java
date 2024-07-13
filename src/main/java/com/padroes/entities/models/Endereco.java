package com.padroes.entities.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "enderecos")
@SQLDelete(sql = "UPDATE enderecos SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String rua;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private Integer cep;
    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Getters e Setters
}
