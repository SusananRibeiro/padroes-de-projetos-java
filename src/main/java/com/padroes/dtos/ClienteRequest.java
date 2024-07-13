package com.padroes.dtos;
import lombok.Data;
import java.time.LocalDate;
@Data
public class ClienteRequest {

    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

    // Getters e Setters
    // toString()
}
