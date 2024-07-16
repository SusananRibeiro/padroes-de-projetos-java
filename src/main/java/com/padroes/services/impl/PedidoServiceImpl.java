package com.padroes.services.impl;
import com.padroes.dtos.PedidoItensResponse;
import com.padroes.dtos.PedidoRequest;
import com.padroes.dtos.PedidoResponse;
import com.padroes.entities.models.Cliente;
import com.padroes.entities.models.Endereco;
import com.padroes.entities.models.Pedido;
import com.padroes.entities.models.PedidoItens;
import com.padroes.entities.repositories.PedidoClienteRepository;
import com.padroes.entities.repositories.PedidoEnderecoRepository;
import com.padroes.entities.repositories.PedidoPedidoItensRepository;
import com.padroes.entities.repositories.PedidoRepository;
import com.padroes.framework.utils.PadraoException;
import com.padroes.mappers.PedidoMapper;
import com.padroes.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements IPedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoClienteRepository pedidoClienteRepository;

    @Autowired
    private PedidoEnderecoRepository pedidoEnderecoRepository;

    @Autowired
    private PedidoPedidoItensRepository pedidoItensRepository;

    @Override
    public List<PedidoResponse> carregarPedidos() {
        List<Pedido> pedidosList = pedidoRepository.findAll();

        List<PedidoResponse> out = pedidosList.stream()
                .map(PedidoMapper::pedidoParaPedidoResponse)
                .collect(Collectors.toList());

        return out;

    }

    @Override
    public PedidoResponse criarPedido(PedidoRequest pedidoRequest) throws PadraoException {
        List<String> mensagens = this.validacaoManutencaoPedido(pedidoRequest);

        if(!mensagens.isEmpty()){
            throw new PadraoException(mensagens);
        }

        Optional<Cliente> cliente = pedidoClienteRepository.findById(pedidoRequest.getClienteId());
        if(!cliente.isPresent()){
            throw new PadraoException("Cliente não encontrado");
        }

        Optional<Endereco> endereco = pedidoEnderecoRepository.findById(pedidoRequest.getEnderecoId());
        if(!endereco.isPresent()){
            throw new PadraoException("Endereço não encontrado");
        }

        Pedido pedidoRetorno = pedidoRepository.save(
                PedidoMapper.pedidoResquestParaPedido(pedidoRequest, cliente.get(), endereco.get()));

        return PedidoMapper.pedidoParaPedidoResponse(pedidoRetorno);

    }

    @Override
    public boolean atualizarPedido(Long id, PedidoRequest pedido) throws PadraoException {
        List<String> mensagens = this.validacaoManutencaoPedido(pedido);

        if(!mensagens.isEmpty()){
            throw new PadraoException(mensagens);
        }

        Optional<Cliente> cliente = pedidoClienteRepository.findById(pedido.getClienteId());
        if(!cliente.isPresent()){
            throw new PadraoException("Cliente não encontrado");
        }

        Optional<Endereco> endereco = pedidoEnderecoRepository.findById(pedido.getEnderecoId());
        if(!endereco.isPresent()){
            throw new PadraoException("Endereço não encontrado");
        }

        Optional<Pedido> pedidosRetorno = pedidoRepository.findById(id).map(record -> {
            record.setValorDesconto(pedido.getValorDesconto());
            record.setClienteId(cliente.get());
            record.setEnderecoId(endereco.get());
            return pedidoRepository.save(record);
        });

        if(pedidosRetorno.isPresent() == false){
            throw new PadraoException("Pedido não encontrado");
        }

        PedidoResponse saida = PedidoMapper.pedidoParaPedidoResponse(pedidosRetorno.get());

        return true;
    }

    @Override
    public void deletarPedido(Long id) throws PadraoException {
        if (!this.pedidoRepository.existsById(id)) {
            throw new PadraoException("Pedido não existe");
        }
        pedidoRepository.deleteById(id);
    }
    @Override
    public PedidoResponse carregarPedidoById(Long id) throws PadraoException {
        Optional<Pedido> optionalPedidos = pedidoRepository.findById(id);

        if(!optionalPedidos.isPresent()) {
            throw new PadraoException("Pedido não encontrado");
        }
        Pedido pedidos = optionalPedidos.get();
        List<PedidoItens> pedidosItensList = pedidoItensRepository.carregarPedidosItensByPedidoId(id);
        PedidoResponse saida = PedidoMapper.pedidoParaPedidoProdutoResponse(pedidos, pedidosItensList);

        double valorTotal = 0;

        for(PedidoItensResponse d : saida.getPedidosItens()) {
            double valorItem = d.getValorUnitario() * d.getQuantidade();
            valorTotal += valorItem;
            System.out.println(d.getValorUnitario());

        }

        saida.setValorTotal(valorTotal - saida.getValorDesconto());

        return saida;

    }

    private List<String> validacaoManutencaoPedido(PedidoRequest pedidoRequest){
        List<String> mensagens = new ArrayList<>();

        if(pedidoRequest.getClienteId() == null || pedidoRequest.getClienteId() < 1){
            mensagens.add("Código do cliente não informado ou inválido!");
        }

        if(pedidoRequest.getEnderecoId() == null || pedidoRequest.getEnderecoId() < 1){
            mensagens.add("Código do endereço não informado ou inválido!");
        }

        return mensagens;
    }
}