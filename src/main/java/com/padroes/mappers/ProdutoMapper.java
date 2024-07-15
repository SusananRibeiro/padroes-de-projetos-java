package com.padroes.mappers;

import com.padroes.dtos.ProdutoRequest;
import com.padroes.dtos.ProdutoResponse;
import com.padroes.entities.models.Produto;

public class ProdutoMapper {
    public static Produto produtoRequestParaProduto(ProdutoRequest produtoRequest){
        Produto saida = new Produto();
        saida.setNome(produtoRequest.getNome());
        saida.setDescricao(produtoRequest.getDescricao());
        return saida;
    }
    public static ProdutoResponse produtoParaProdutoResponse(Produto produtos){
        ProdutoResponse saida = new ProdutoResponse();
        saida.setId(produtos.getId());
        saida.setNome(produtos.getNome());
        saida.setDescricao(produtos.getDescricao());
        return saida;
    }



}
