package com.padroes.dtos;

import lombok.Data;

@Data
public class PedidoItensRequest {
    private Integer quantidade;
    private Double valorUnitario;
    private Long produtoId;
    private Long pedidoId;

    // Getters e Setters
    // toString()
//    public Integer getQuantidade() {
//        return quantidade;
//    }
//
//    public void setQuantidade(Integer quantidade) {
//        this.quantidade = quantidade;
//    }
//
//    public Double getValorUnitario() {
//        return valorUnitario;
//    }
//
//    public void setValorUnitario(Double valorUnitario) {
//        this.valorUnitario = valorUnitario;
//    }
//
//    public Long getProdutoId() {
//        return produtoId;
//    }
//
//    public void setProdutoId(Long produtoId) {
//        this.produtoId = produtoId;
//    }
//
//    public Long getPedidoId() {
//        return pedidoId;
//    }
//
//    public void setPedidoId(Long pedidoId) {
//        this.pedidoId = pedidoId;
//    }
}
