package com.padroes.services.impl;
import com.padroes.dtos.ClienteRequest;
import com.padroes.dtos.ClienteResponse;
import com.padroes.entities.models.Cliente;
import com.padroes.entities.models.Endereco;
import com.padroes.entities.repositories.ClienteEnderecoRepository;
import com.padroes.entities.repositories.ClienteRepository;
import com.padroes.framework.utils.PadraoException;
import com.padroes.framework.utils.StringUtil;
import com.padroes.mappers.ClienteMapper;
import com.padroes.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteEnderecoRepository clienteEnderecoRepository;
    // GET todos
    @Override
    public List<ClienteResponse> carregarClietes() {
        List<Cliente> clienteLista = clienteRepository.findAll();
        List<ClienteResponse> saida = clienteLista
                .stream()
                .map(ClienteMapper::clienteParaClienteResponse)
                .collect(Collectors.toList());
        return saida;
    }

    // POST
    @Override
    public ClienteResponse criarCliente(ClienteRequest clienteRequest) throws PadraoException {
        List<String> messages = this.validacaoManutencaoCliente(clienteRequest);
        if(!messages.isEmpty()){
            throw new PadraoException(messages);
        }
        Cliente cliente = ClienteMapper.clienteRequestParaCliente(clienteRequest);
        Cliente resultadoCliente = clienteRepository.save(cliente);
        ClienteResponse saida = ClienteMapper.clienteParaClienteResponse(resultadoCliente);
        return saida;
    }

    // PUT
    @Override
    public ClienteResponse atualizarCliente(Long id, ClienteRequest clienteRequest) throws PadraoException {
        List<String> mensagens = this.validacaoManutencaoCliente(clienteRequest);
        if(!mensagens.isEmpty()){
            throw new PadraoException(mensagens);
        }
        Optional<Cliente> cliente = clienteRepository.findById(id).map(record -> {
            record.setNome(clienteRequest.getNome());
            record.setSobrenome(clienteRequest.getSobrenome());
            record.setDataNascimento(clienteRequest.getDataNascimento());
            record.setTelefone(clienteRequest.getTelefone());
            record.setEmail(clienteRequest.getEmail());
            return clienteRepository.save(record);
        });

        if(!cliente.isPresent()) {
            throw new PadraoException("Cliente informado não existe!");
        }

        ClienteResponse saida = ClienteMapper.clienteParaClienteResponse(cliente.get());

        return saida;
    }

    // Delete
    @Override
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // GET por ID
    @Override
    public ClienteResponse carregarClienteById(Long id) throws PadraoException {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if(!optionalCliente.isPresent()) {
            throw new PadraoException("Cliente não encontrado!");
        }
        Cliente cliente = optionalCliente.get();
        List<Endereco> enderecoCliente = clienteEnderecoRepository.carregarEnderecoByClienteId(id);
        ClienteResponse saida = ClienteMapper.clienteParaClienteResponse(cliente, enderecoCliente);
        return saida;
    }

    private List<String> validacaoManutencaoCliente(ClienteRequest clienteRequest) {
        List<String> mensagens = new ArrayList<>();
        if(StringUtil.validarString(clienteRequest.getNome())) {
            mensagens.add("Nome do cliente é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getSobrenome())) {
            mensagens.add("Sobrenome do cliente é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getEmail())) {
            mensagens.add("E-mail é obrigatório.");
        }
        return mensagens;
    }
}
