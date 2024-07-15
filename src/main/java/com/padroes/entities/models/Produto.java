package com.padroes.entities.models;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Entity(name = "produtos")
@SQLDelete(sql = "UPDATE produtos SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


    // Getters e Setters
}
