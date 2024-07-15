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

    // Getters e Setters
    // toString()

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
//    public double getValorDesconto() {
//        return valorDesconto;
//    }
//
//    public void setValorDesconto(double valorDesconto) {
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
}
