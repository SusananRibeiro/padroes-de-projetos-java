package com.padroes.services.impl;
import com.padroes.dtos.ProdutoRequest;
import com.padroes.dtos.ProdutoResponse;
import com.padroes.entities.models.Produto;
import com.padroes.entities.repositories.ProdutoRepository;
import com.padroes.framework.utils.PadraoException;
import com.padroes.framework.utils.StringUtil;
import com.padroes.mappers.ProdutoMapper;
import com.padroes.services.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ProdutoServiceImpl implements IProdutoService {
    @Autowired
    private ProdutoRepository produtosRepository;
    @Override
    public List<ProdutoResponse> carregarProdutos() {
        List<Produto> produtosList = produtosRepository.findAll();
        List<ProdutoResponse> out = produtosList
                .stream()
                .map(ProdutoMapper::produtoParaProdutoResponse)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public ProdutoResponse criarProduto(ProdutoRequest produtosRequestDom) throws PadraoException {
        List<String> messages = this.validacaoManutencaoProduto(produtosRequestDom);

        if(!messages.isEmpty()){
            throw new PadraoException(messages);
        }

        Produto produtos = ProdutoMapper.produtoRequestParaProduto(produtosRequestDom);
        Produto resultProdutos = produtosRepository.save(produtos);
        ProdutoResponse out = ProdutoMapper.produtoParaProdutoResponse(resultProdutos);

        return out;

    }

    @Override
    public ProdutoResponse atualizarProduto(Long id, ProdutoRequest produtoRequest) throws PadraoException {
        List<String> mensagens = this.validacaoManutencaoProduto(produtoRequest);

        if(!mensagens.isEmpty()){
            throw new PadraoException(mensagens);
        }

        Optional<Produto> produtos = produtosRepository.findById(id).map(record -> {
            record.setNome(produtoRequest.getNome());
            record.setDescricao(produtoRequest.getDescricao());
            return produtosRepository.save(record);
        });

        if(!produtos.isPresent()){
            throw new PadraoException("Produto informando não existe!");
        }

        ProdutoResponse saida = ProdutoMapper.produtoParaProdutoResponse(produtos.get());

        return saida;
    }

    @Override
    public void deletarProduto(Long id) {
        produtosRepository.deleteById(id);
    }

    @Override
    public ProdutoResponse carregarProdutoById(Long id) throws PadraoException {
        Optional<Produto> optionalProduto = produtosRepository.findById(id);

        if(!optionalProduto.isPresent()) {
            throw new PadraoException("Produto não encontrado");
        }
        Produto produto = optionalProduto.get();

        ProdutoResponse saida = ProdutoMapper.produtoParaProdutoResponse(produto);

        return saida;

    }

    private List<String> validacaoManutencaoProduto(ProdutoRequest produtoRequest){
        List<String> mensagens = new ArrayList<>();

        if(StringUtil.validarString(produtoRequest.getNome())){
            mensagens.add("Produto informado não possui nome!");
        }

        if(StringUtil.validarString(produtoRequest.getDescricao())){
            mensagens.add("Produto informado não possui descrição!");
        }

        return mensagens;
    }
}
