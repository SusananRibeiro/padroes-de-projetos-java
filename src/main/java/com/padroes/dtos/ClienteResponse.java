package com.padroes.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ClienteResponse {
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private List<ClienteEnderecoResponse> enderecos;

    // Getters e Setters
    // toString()
}
