package com.padroes.services.impl;
import com.padroes.dtos.EnderecoRequest;
import com.padroes.dtos.EnderecoResponse;
import com.padroes.entities.models.Cliente;
import com.padroes.entities.models.Endereco;
import com.padroes.entities.repositories.EnderecoClienteRepository;
import com.padroes.entities.repositories.EnderecoRepository;
import com.padroes.framework.utils.PadraoException;
import com.padroes.mappers.EnderecoMapper;
import com.padroes.services.IEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EnderecoServiceImpl implements IEnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;
    @Override
    public List<EnderecoResponse> carregarEnderecos() {
        List<Endereco> enderecoLista = enderecoRepository.findAll();
        List<EnderecoResponse> saida = enderecoLista
                .stream()
                .map(EnderecoMapper::enderecoParaEnderecoResponse)
                .collect(Collectors.toList());
        return saida;
    }

    @Override
    public EnderecoResponse criarEndereco(EnderecoRequest enderecoRequest) throws PadraoException {
        List<String> mensagens = this.validacaoManutencaoEndereco(enderecoRequest);
        if(!mensagens.isEmpty()){
            throw new PadraoException(mensagens);
        }

        Optional<Cliente> cliente = enderecoClienteRepository.findById(enderecoRequest.getClienteId());
        if(!cliente.isPresent()) {
            throw new PadraoException("Cliente não encontrado!");
        }

        Endereco endereco = EnderecoMapper.enderecoRequestParaEndereco(enderecoRequest, cliente.get());
        Endereco resultadoEndereco = enderecoRepository.save(endereco);
        EnderecoResponse saida = EnderecoMapper.enderecoParaEnderecoResponse(resultadoEndereco);

        return saida;
    }

    @Override
    public EnderecoResponse atualizarEndereco(Long id, EnderecoRequest enderecoRequest) throws PadraoException {
        List<String> mensagens = this.validacaoManutencaoEndereco(enderecoRequest);
        if(!mensagens.isEmpty()){
            throw new PadraoException(mensagens);
        }

        Optional<Cliente> cliente = enderecoClienteRepository.findById(enderecoRequest.getClienteId());
        if(!cliente.isPresent()) {
            throw new PadraoException("Cliente não encontrado!");
        }

        Optional<Endereco> endereco = enderecoRepository.findById(id).map(record -> {
            record.setRua(enderecoRequest.getRua());
            record.setBairro(enderecoRequest.getBairro());
            record.setCidade(enderecoRequest.getCidade());
            record.setEstado(enderecoRequest.getEstado());
            record.setCep(enderecoRequest.getCep());
            record.setCliente(cliente.get());
            return enderecoRepository.save(record);
        });

        if(!endereco.isPresent()) {
            throw new PadraoException("Endereço informado não existe!");
        }

        EnderecoResponse saida = EnderecoMapper.enderecoParaEnderecoResponse(endereco.get());

        return saida;
    }

    @Override
    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public EnderecoResponse carregarEnderecoById(Long id) throws PadraoException {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        if(!optionalEndereco.isPresent()) {
            throw new PadraoException("Endereço não encontrado!");
        }
        Endereco endereco = optionalEndereco.get();
        EnderecoResponse saida = EnderecoMapper.enderecoParaEnderecoResponse(endereco);
        return saida;
    }

    private List<String> validacaoManutencaoEndereco(EnderecoRequest enderecoRequest) {
        List<String> mensagens = new ArrayList<>();
        if(enderecoRequest.getRua() == null || enderecoRequest.getRua() == "") {
            mensagens.add("Nome da rua é obrigatório.");
        }
        if(enderecoRequest.getBairro() == null || enderecoRequest.getBairro() == "") {
            mensagens.add("Bairro é obrigatório.");
        }
        if(enderecoRequest.getCidade() == null || enderecoRequest.getCidade() == "") {
            mensagens.add("Cidade é obrigatório.");
        }
        if(enderecoRequest.getEstado() == null || enderecoRequest.getEstado() == "") {
            mensagens.add("Estado é obrigatório.");
        }
        if(enderecoRequest.getClienteId() == null) {
            mensagens.add("Cliente é obrigatório.");
        }

        return mensagens;
    }
}
