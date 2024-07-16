package com.padroes.dtos;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PedidoRequest {

    private LocalDateTime dataCriacao;

    private LocalDate dataEntrega;

    private double valorDesconto;

    private Long clienteId;

    private Long enderecoId;

// Não precisa gerar Getters, Setters e toString(), pois o "@Data" vai fazer isso
}
