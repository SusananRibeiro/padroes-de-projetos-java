package com.padroes.services;

import com.padroes.dtos.PedidoRequest;
import com.padroes.dtos.PedidoResponse;
import com.padroes.framework.utils.PadraoException;
import java.util.List;
public interface IPedidoService {
    List<PedidoResponse> carregarPedidos();
    PedidoResponse criarPedido(PedidoRequest pedido) throws PadraoException;
    boolean atualizarPedido(Long id, PedidoRequest pedido) throws PadraoException;
    void deletarPedido(Long id) throws PadraoException;
    PedidoResponse carregarPedidoById(Long id) throws PadraoException;
}
