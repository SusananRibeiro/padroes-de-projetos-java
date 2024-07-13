package com.padroes.dtos;

import com.padroes.entities.models.Cliente;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EnderecoRequest {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer cep;
    private LocalDateTime deleteAt;
    private Cliente cliente;

    // Getters e Setters
    // toString()
}
