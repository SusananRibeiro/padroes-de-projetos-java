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
//    public Integer getQuantidade() {
//        return quantidade;
//    }
//
//    public void setQuantidade(Integer quantidade) {
//        this.quantidade = quantidade;
//    }
//
//    public Double getValorUnitario() {
//        return this.valorUnitario;
//    }
//
//    public void setValorUnitario(Double valorUnitario) {
//        this.valorUnitario = valorUnitario;
//    }
//
//    public Long getProdutoId(Produtos produtoId) {
//        return this.produtoId;
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
//
//    public String getNomeDoProduto() {
//        return nomeDoProduto;
//    }
//
//    public void setNomeDoProduto(String nomeDoProduto) {
//        this.nomeDoProduto = nomeDoProduto;
//    }


}
