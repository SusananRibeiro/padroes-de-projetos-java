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

    // Getters e Setters
    // toString()

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public LocalDateTime getDataCriacao() {
//        return dataCriacao;
//    }
//
//    public void setDataCriacao(LocalDateTime dataCriacao) {
//        this.dataCriacao = dataCriacao;
//    }
//
//    public LocalDate getDataEntrega() {
//        return dataEntrega;
//    }
//
//    public void setDataEntrega(LocalDate dataEntrega) {
//        this.dataEntrega = dataEntrega;
//    }
//
//    public Double getValorDesconto() {
//        return valorDesconto;
//    }
//
//    public void setValorDesconto(Double valorDesconto) {
//        this.valorDesconto = valorDesconto;
//    }
//
//    public Long getClienteId() {
//        return clienteId;
//    }
//
//    public void setClienteId(Long clienteId) {
//        this.clienteId = clienteId;
//    }
//
//    public Long getEnderecoId() {
//        return enderecoId;
//    }
//
//    public void setEnderecoId(Long enderecoId) {
//        this.enderecoId = enderecoId;
//    }
//
//    public List<PedidosPedidosItensResponse> getPedidosItens() {
//        return pedidosItens;
//    }
//
//    public void setPedidosItens(List<PedidosPedidosItensResponse> pedidosItens) {
//        this.pedidosItens = pedidosItens;
//    }
//
//    public Double getValorTotal() {
//        return valorTotal;
//    }
//
//    public void setValorTotal(Double valorTotal) {
//        this.valorTotal = valorTotal;
//    }
//
//    @Override
//    public String toString() {
//        return "PedidosResponseDom{" +
//                "id=" + id +
//                ", dataCriacao=" + dataCriacao +
//                ", dataEntrega=" + dataEntrega +
//                ", valorDesconto=" + valorDesconto +
//                ", clienteId=" + clienteId +
//                ", enderecoId=" + enderecoId +
//                ", pedidosItens=" + pedidosItens +
//                ", valorTotal=" + valorTotal +
//                '}';
//    }
}
