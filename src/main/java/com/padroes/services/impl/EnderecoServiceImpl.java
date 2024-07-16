package com.padroes.services.impl;
import com.padroes.dtos.EnderecoRequest;
import com.padroes.dtos.EnderecoResponse;
import com.padroes.entities.models.Cliente;
import com.padroes.entities.models.Endereco;
import com.padroes.entities.repositories.EnderecoClienteRepository;
import com.padroes.entities.repositories.EnderecoRepository;
import com.padroes.framework.utils.PadraoException;
import com.padroes.framework.utils.StringUtil;
import com.padroes.framework.utils.enums.EstadosDoBrasil;
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
    public boolean atualizarEndereco(Long id, EnderecoRequest enderecoRequest) throws PadraoException {
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
            record.setNumero(enderecoRequest.getNumero());
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

        return true;
    }

    @Override
    public void deletarEndereco(Long id) throws PadraoException {
        if (!this.enderecoRepository.existsById(id)) {
            throw new PadraoException("Endereço não existe");
        }
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
        if(StringUtil.validarString(String.valueOf(enderecoRequest.getEstado()))){
            mensagens.add("A sigla do estado é obrigatório.");
        }
        if(verificarUFDoEstado(String.valueOf(enderecoRequest.getEstado())) == false) {
            mensagens.add("Estado é obrigatório.");
        }
        if(StringUtil.validarString(String.valueOf(enderecoRequest.getCep()))){
            mensagens.add("O CEP é obrigatório.");
        }
        if(!Integer.toString(enderecoRequest.getCep()).matches("^[0-9]{8}$")){
            mensagens.add("CEP invalido.");
        }
        if(enderecoRequest.getClienteId() == null) {
            mensagens.add("Cliente é obrigatório.");
        }
        if(!Long.toString(enderecoRequest.getClienteId()).matches("^[0-9]$")) {
            mensagens.add("Cliente invalido.");
        }

        return mensagens;
    }

    // Validação estados
    public boolean verificarUFDoEstado(String uf) {
        EstadosDoBrasil estadosDoBrasil = EstadosDoBrasil.valueOf(uf);
        try {
            estadosDoBrasil.valueOf(uf);
            return true; // O estado é válido
        } catch (IllegalArgumentException e) {
            return false; // O estado é inválido
        }
    }
}
