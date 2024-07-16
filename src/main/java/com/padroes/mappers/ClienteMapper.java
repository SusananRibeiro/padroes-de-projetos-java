package com.padroes.mappers;

import com.padroes.dtos.ClienteEnderecoResponse;
import com.padroes.dtos.ClienteRequest;
import com.padroes.dtos.ClienteResponse;
import com.padroes.entities.models.Cliente;
import com.padroes.entities.models.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {
    public static Cliente clienteRequestParaCliente(ClienteRequest clienteRequest) {
        Cliente saida = new Cliente();
        saida.setNome(clienteRequest.getNome());
        saida.setSobrenome(clienteRequest.getSobrenome());
        saida.setCpf(clienteRequest.getCpf());
        saida.setDataNascimento(clienteRequest.getDataNascimento());
        saida.setTelefone(clienteRequest.getTelefone());
        saida.setEmail(clienteRequest.getEmail());
        return saida;

    }

    public static ClienteResponse clienteParaClienteResponse(Cliente cliente) {
        ClienteResponse saida = new ClienteResponse();
        saida.setId(cliente.getId());
        saida.setNome(cliente.getNome());
        saida.setSobrenome(cliente.getSobrenome());
        saida.setCpf(cliente.getCpf());
        saida.setDataNascimento(cliente.getDataNascimento());
        saida.setTelefone(cliente.getTelefone());
        saida.setEmail(cliente.getEmail());
        return saida;
    }

    public static ClienteResponse clienteParaClienteResponse(Cliente cliente, List<Endereco> endereco) {
        ClienteResponse saida = ClienteMapper.clienteParaClienteResponse(cliente);
        List<ClienteEnderecoResponse> enderecoResponseList = endereco.stream()
                .map(ClienteMapper::enderecoParaClienteEnderecoResponse)
                .collect(Collectors.toList());
        saida.setEnderecos(enderecoResponseList);
        return saida;
    }

    public static ClienteEnderecoResponse enderecoParaClienteEnderecoResponse(Endereco endereco) {
        ClienteEnderecoResponse saida = new ClienteEnderecoResponse();
        saida.setId(endereco.getId());
        saida.setRua(endereco.getRua());
        saida.setBairro(endereco.getBairro());
        saida.setCidade(endereco.getCidade());
        saida.setEstado(endereco.getEstado());
        saida.setCep(endereco.getCep());
        saida.setPais(endereco.getPais());
        return saida;

    }
}
