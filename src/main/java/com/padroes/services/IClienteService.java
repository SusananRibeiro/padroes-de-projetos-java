package com.padroes.services;

import com.padroes.dtos.ClienteRequest;
import com.padroes.dtos.ClienteResponse;
import com.padroes.framework.utils.PadraoException;
import java.util.List;

public interface IClienteService {
    List<ClienteResponse> carregarClientes();
    ClienteResponse criarCliente(ClienteRequest clienteRequest) throws PadraoException;
    boolean atualizarCliente(Long id, ClienteRequest clienteRequest) throws PadraoException;
   void deletarCliente(Long id) throws PadraoException;
    ClienteResponse carregarClienteById(Long id) throws PadraoException;

}
