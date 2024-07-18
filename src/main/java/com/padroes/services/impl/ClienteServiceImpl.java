package com.padroes.services.impl;
import com.padroes.dtos.ClienteEnderecoResponse;
import com.padroes.dtos.ClienteRequest;
import com.padroes.dtos.ClienteResponse;
import com.padroes.entities.models.Cliente;
import com.padroes.entities.models.Endereco;
import com.padroes.entities.repositories.ClienteEnderecoRepository;
import com.padroes.entities.repositories.ClienteRelatorioRepository;
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
    @Autowired
    private ClienteRelatorioRepository clienteRelatorioRepository;
    // GET todos
    @Override
    public List<ClienteResponse> carregarClientes() {
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
    public boolean atualizarCliente(Long id, ClienteRequest clienteRequest) throws PadraoException {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            // Implemente suas próprias validações aqui, se necessário
            validacaoManutencaoCliente(clienteRequest);

            // Verificar se o CPF do cliente existente corresponde ao CPF informado na requisição
            if (!cliente.getCpf().equals(clienteRequest.getCpf())) {
                // CPF informado é diferente do CPF armazenado para este cliente, então valida o CPF existente
                if (validarCPF(clienteRequest.getCpf())) {
                    throw new PadraoException("CPF já cadastrado para outro cliente!");
                }
            }
            // Impedir a alteração do CPF
            if (!cliente.getCpf().equals(clienteRequest.getCpf())) {
                throw new PadraoException("Não é permitido alterar o CPF do cliente!");
            }
            // Atualizar os dados do cliente (exceto CPF)
            cliente.setNome(clienteRequest.getNome());
            cliente.setSobrenome(clienteRequest.getSobrenome());
            cliente.setDataNascimento(clienteRequest.getDataNascimento());
            cliente.setTelefone(clienteRequest.getTelefone());
            cliente.setEmail(clienteRequest.getEmail());

            // Salvar as alterações no banco de dados
            Cliente clienteAtualizado = clienteRepository.save(cliente);

            // Criar e retornar a resposta de sucesso com os dados atualizados do cliente
            ClienteResponse out = ClienteMapper.clienteParaClienteResponse(clienteAtualizado);
            return true;
        } else {
            throw new PadraoException("Cliente informado não existe!");
        }
    }

    // Delete
    @Override
    public void deletarCliente(Long id) throws PadraoException {
        if (!this.clienteRepository.existsById(id)) {
            throw new PadraoException("Cliente não existe");
        }
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
        if(validarCPF(clienteRequest.getCpf())) {
            mensagens.add("CPF já cadastrado para outro cliente!");
        }
        if(StringUtil.validarString(clienteRequest.getCpf())) {
            mensagens.add("CPF é obrigatório.");
        }
        if(StringUtil.validarString(clienteRequest.getEmail())) {
            mensagens.add("E-mail é obrigatório.");
        }
        return mensagens;
    }

    // Validação do CPF
    public boolean validarCPF(String cpf) {
        return clienteRelatorioRepository.existsByCpf(cpf);
    }
}
