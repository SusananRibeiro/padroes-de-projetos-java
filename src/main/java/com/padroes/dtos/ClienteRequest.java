package com.padroes.dtos;
import lombok.Data;
import java.time.LocalDate;
@Data
public class ClienteRequest {

    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

// Não precisa gerar Getters, Setters e toString(), pois o "@Data" vai fazer isso
}
