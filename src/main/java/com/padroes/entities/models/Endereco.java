package com.padroes.entities.models;
import com.padroes.framework.utils.enums.EstadosDoBrasil;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;
@Data
@Entity(name = "enderecos")
@SQLDelete(sql = "UPDATE enderecos SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String rua;
    @Column
    private String numero;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private EstadosDoBrasil estado;
    @Column(nullable = false, length = 8)
    private Integer cep;
    @Column(nullable = false)
    private String pais;
    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Getters e Setters
}
