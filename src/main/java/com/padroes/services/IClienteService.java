package com.padroes.services;

import com.padroes.dtos.ClienteRequest;
import com.padroes.dtos.ClienteResponse;
import com.padroes.framework.utils.PadraoException;
import java.util.List;

public interface IClienteService {
    List<ClienteResponse> carregarClietes();
    ClienteResponse criarCliente(ClienteRequest clienteRequest) throws PadraoException;
    boolean atualizarCliente(Long id, ClienteRequest clienteRequest) throws PadraoException;
    String deletarCliente(Long id) throws PadraoException;
    ClienteResponse carregarClienteById(Long id) throws PadraoException;

}
