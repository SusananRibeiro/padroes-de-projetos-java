package com.padroes.services;

import com.padroes.dtos.ClienteRequest;
import com.padroes.dtos.ClienteResponse;
import com.padroes.framework.utils.PadraoException;
import java.util.List;

public interface IClienteService {
    List<ClienteResponse> carregarClietes();
    ClienteResponse criarCliente(ClienteRequest clienteRequest) throws PadraoException;
    ClienteResponse atualizarCliente(Long id, ClienteRequest clienteRequest) throws PadraoException;
    void deletarCliente(Long id);
    ClienteResponse carregarClienteById(Long id) throws PadraoException;

}
