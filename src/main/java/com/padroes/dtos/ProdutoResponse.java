package com.padroes.dtos;

import lombok.Data;

@Data
public class ProdutoResponse {
    private Long id;
    private String nome;
    private String descricao;

// Não precisa gerar Getters, Setters e toString(), pois o "@Data" vai fazer isso

}
