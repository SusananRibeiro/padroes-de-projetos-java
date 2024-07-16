package com.padroes.entities.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "clientes")
@SQLDelete(sql = "UPDATE clientes SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobrenome;
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
    @Column
    private LocalDate dataNascimento;
    @Column(length = 12)
    private String telefone;
    @Column(nullable = false)
    private String email;
    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    // Getters e Setters

}
