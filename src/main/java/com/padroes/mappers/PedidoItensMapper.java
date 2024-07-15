package com.padroes.mappers;

import com.padroes.dtos.PedidoItensRequest;
import com.padroes.dtos.PedidoItensResponse;
import com.padroes.entities.models.Pedido;
import com.padroes.entities.models.PedidoItens;
import com.padroes.entities.models.Produto;

public class PedidoItensMapper {

    public static PedidoItens pedidoItensResquestParaPedidoItens(PedidoItensRequest pedidosItens,
                                                                 Produto produto, Pedido pedido){
        PedidoItens saida = new PedidoItens();
        saida.setQuantidade(pedidosItens.getQuantidade());
        saida.setValorUnitario(pedidosItens.getValorUnitario());
        saida.setProdutoId(produto);
        saida.setPedidoId(pedido);
        return saida;
    }

    public static PedidoItensResponse pedidoItensParaPedidoItensResponse(PedidoItens pedidosItens){
        PedidoItensResponse saida = new PedidoItensResponse();
        saida.setId(pedidosItens.getId());
        saida.setQuantidade(pedidosItens.getQuantidade());
        saida.setValorUnitario(pedidosItens.getValorUnitario());
        saida.setProdutoId(pedidosItens.getProdutoId().getId());
        saida.setPedidoId(pedidosItens.getPedidoId().getId());
        saida.setNomeDoProduto(pedidosItens.getProdutoId().getNome());
        return saida;
    }

}
