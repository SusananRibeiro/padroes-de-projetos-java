package com.padroes.dtos;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponse {
    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDate dataEntrega;

    private Double valorDesconto;

    private Long clienteId;

    private Long enderecoId;

    private List<PedidoPedidoItensResponse> pedidosItens;

    private Double valorTotal;

    // Não precisa gerar Getters, Setters e toString(), pois o "@Data" vai fazer isso
}
