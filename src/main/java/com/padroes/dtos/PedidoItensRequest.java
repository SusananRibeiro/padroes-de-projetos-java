package com.padroes.dtos;

import lombok.Data;

@Data
public class PedidoItensRequest {
    private Integer quantidade;
    private Double valorUnitario;
    private Long produtoId;
    private Long pedidoId;

    // Não precisa gerar Getters, Setters e toString(), pois o "@Data" vai fazer isso
}
