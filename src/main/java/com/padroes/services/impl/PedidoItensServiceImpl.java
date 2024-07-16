package com.padroes.services.impl;
import com.padroes.dtos.PedidoItensRequest;
import com.padroes.dtos.PedidoItensResponse;
import com.padroes.entities.models.Pedido;
import com.padroes.entities.models.PedidoItens;
import com.padroes.entities.models.Produto;
import com.padroes.entities.repositories.PedidoItensPedidoRepository;
import com.padroes.entities.repositories.PedidoItensProdutoRepository;
import com.padroes.entities.repositories.PedidoItensRepository;
import com.padroes.framework.utils.PadraoException;
import com.padroes.mappers.PedidoItensMapper;
import com.padroes.services.IPedidoItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PedidoItensServiceImpl implements IPedidoItensService {
    @Autowired
    private PedidoItensRepository pedidoItensRepository;
    @Autowired
    private PedidoItensProdutoRepository pedidoItensProdutoRepository;
    @Autowired
    private PedidoItensPedidoRepository pedidoItensPedidoRepository;

    @Override
    public List<PedidoItensResponse> carregarPedidosItens() {
        List<PedidoItens> pedidosItensList = pedidoItensRepository.findAll();

        List<PedidoItensResponse> out = pedidosItensList.stream()
                .map(PedidoItensMapper::pedidoItensParaPedidoItensResponse)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public PedidoItensResponse criarPedidoItens(PedidoItensRequest pedidoItens) throws PadraoException {
        List<String> messages = this.validacaoManutencaoPedidoItens(pedidoItens);

        if(!messages.isEmpty()){
            throw new PadraoException(messages);
        }

        Optional<Produto> produto = pedidoItensProdutoRepository.findById(pedidoItens.getProdutoId());
        if(!produto.isPresent()){
            throw new PadraoException("Produto não encontrado");
        }

        Optional<Pedido> pedido = pedidoItensPedidoRepository.findById(pedidoItens.getPedidoId());
        if(!pedido.isPresent()){
            throw new PadraoException("Pedido não encontrado");
        }

        PedidoItens pedidoItensRetorno = pedidoItensRepository.save(PedidoItensMapper
                .pedidoItensResquestParaPedidoItens(pedidoItens, produto.get(), pedido.get()));

        return PedidoItensMapper.pedidoItensParaPedidoItensResponse(pedidoItensRetorno);
    }

    @Override
    public boolean atualizarPedidoItens(Long id, PedidoItensRequest pedidoItensRequest) throws PadraoException {
        List<String> mensagens = this.validacaoManutencaoPedidoItens(pedidoItensRequest);

        if(!mensagens.isEmpty()){
            throw new PadraoException(mensagens);
        }

        Optional<Produto> produto = pedidoItensProdutoRepository.findById(pedidoItensRequest.getProdutoId());
        if(!produto.isPresent()){
            throw new PadraoException("Produto não encontrado");
        }

        Optional<Pedido> pedido = pedidoItensPedidoRepository.findById(pedidoItensRequest.getPedidoId());
        if(!pedido.isPresent()){
            throw new PadraoException("Pedido não encontrado");
        }

        Optional<PedidoItens> pedidosItensRetorno = pedidoItensRepository.findById(id).map(record -> {
            record.setQuantidade(pedidoItensRequest.getQuantidade());
            record.setValorUnitario(pedidoItensRequest.getValorUnitario());
            record.setProdutoId(produto.get());
            record.setPedidoId(pedido.get());
            return pedidoItensRepository.save(record);
        });

        if(pedidosItensRetorno.isPresent() == false){
            throw new PadraoException("Item do Pedido não encontrado");
        }

        PedidoItensResponse saida = PedidoItensMapper.pedidoItensParaPedidoItensResponse(pedidosItensRetorno.get());

        return true;
    }

    @Override
    public void deletarPedidoItens(Long id) throws PadraoException {
        if (!this.pedidoItensRepository.existsById(id)) {
            throw new PadraoException("Endereço não existe");
        }
        pedidoItensRepository.deleteById(id);
    }

    @Override
    public PedidoItensResponse carregarPedidoItensById(Long id) throws PadraoException {
        Optional<PedidoItens> optionalPedidosItens = pedidoItensRepository.findById(id);
        if(!optionalPedidosItens.isPresent()) {
            throw new PadraoException("Item do Pedido não encontrado");
        }
        PedidoItens pedidosItens = optionalPedidosItens.get();
        PedidoItensResponse saida = PedidoItensMapper.pedidoItensParaPedidoItensResponse(pedidosItens);
        return saida;

    }

//    @Override
//    public PedidosItens carregarPedidoItensEntidade(Long id) {
//        PedidosItens pedidosItens = pedidoItensRepository.findById(id).get();
//
//        return pedidosItens;
//    }

    private List<String> validacaoManutencaoPedidoItens(PedidoItensRequest pedidoItensRequest){
        List<String> mensagens = new ArrayList<>();

        if(pedidoItensRequest.getQuantidade() == null || pedidoItensRequest.getQuantidade() < 1){
            mensagens.add("Quantidade não informada ou inválida!");
        }

        if(pedidoItensRequest.getValorUnitario() == null || pedidoItensRequest.getValorUnitario() < 1){
            mensagens.add("Valor unitário não informado ou inválido!");
        }

        if(pedidoItensRequest.getProdutoId() == null || pedidoItensRequest.getProdutoId() < 1){
            mensagens.add("Código do produto não informado ou inválido!");
        }

        if(pedidoItensRequest.getPedidoId() == null || pedidoItensRequest.getPedidoId() < 1){
            mensagens.add("Código do pedido não informado ou inválido!");
        }

        return mensagens;
    }
}
