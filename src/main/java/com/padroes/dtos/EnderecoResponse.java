package com.padroes.dtos;

import lombok.Data;

@Data
public class EnderecoResponse {
    private Long id;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer cep;
    private Long clienteId;

    // Getters e Setters
    // toString()
}
