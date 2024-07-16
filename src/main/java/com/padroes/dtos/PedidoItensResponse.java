package com.padroes.dtos;
import lombok.Data;

@Data
public class PedidoItensResponse {
    private Long id; // id
    private Integer quantidade;
    private Double valorUnitario;
    private Long produtoId;
    private Long pedidoId;
    private String nomeDoProduto;

// Não precisa gerar Getters, Setters e toString(), pois o "@Data" vai fazer isso


}
