package com.padroes.services;
import com.padroes.dtos.PedidoItensRequest;
import com.padroes.dtos.PedidoItensResponse;
import com.padroes.framework.utils.PadraoException;
import java.util.List;

public interface IPedidoItensService {
    List<PedidoItensResponse> carregarPedidosItens();
    PedidoItensResponse criarPedidoItens(PedidoItensRequest pedidoItens) throws PadraoException;
    PedidoItensResponse atualizarPedidoItens(Long id, PedidoItensRequest pedidoItens) throws PadraoException;
    void deletarPedidoItens(Long id);
    PedidoItensResponse carregarPedidoItensById(Long id) throws PadraoException;
}
