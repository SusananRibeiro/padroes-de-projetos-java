package com.padroes.mappers;

import com.padroes.dtos.EnderecoRequest;
import com.padroes.dtos.EnderecoResponse;
import com.padroes.entities.models.Cliente;
import com.padroes.entities.models.Endereco;

public class EnderecoMapper {

    public static Endereco enderecoRequestParaEndereco(EnderecoRequest enderecoRequest, Cliente cliente) {
        Endereco saida = new Endereco();
        saida.setRua(enderecoRequest.getRua());
        saida.setNumero(enderecoRequest.getNumero());
        saida.setBairro(enderecoRequest.getBairro());
        saida.setCidade(enderecoRequest.getCidade());
        saida.setEstado(enderecoRequest.getEstado());
        saida.setCep(enderecoRequest.getCep());
        saida.setPais(enderecoRequest.getPais());
        saida.setCliente(cliente);
        return saida;

    }

    public static EnderecoResponse enderecoParaEnderecoResponse(Endereco endereco) {
        EnderecoResponse saida = new EnderecoResponse();
        saida.setId(endereco.getId());
        saida.setRua(endereco.getRua());
        saida.setNumero(endereco.getNumero());
        saida.setBairro(endereco.getBairro());
        saida.setCidade(endereco.getCidade());
        saida.setEstado(endereco.getEstado());
        saida.setCep(endereco.getCep());
        saida.setPais(endereco.getPais());
        saida.setClienteId(endereco.getCliente().getId());
        return saida;
    }
}
