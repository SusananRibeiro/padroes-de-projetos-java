package com.padroes.services;

import com.padroes.dtos.EnderecoRequest;
import com.padroes.dtos.EnderecoResponse;
import com.padroes.framework.utils.PadraoException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface IEnderecoService {
    List<EnderecoResponse> carregarEnderecos();
    EnderecoResponse criarEndereco(EnderecoRequest enderecoRequest) throws PadraoException;
    EnderecoResponse atualizarEndereco(Long id, EnderecoRequest enderecoRequest) throws PadraoException;
    void deletarEndereco(Long id);
    EnderecoResponse carregarEnderecoById(Long id) throws PadraoException;
}
