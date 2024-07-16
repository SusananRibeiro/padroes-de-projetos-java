package com.padroes.services;
import com.padroes.dtos.ProdutoRequest;
import com.padroes.dtos.ProdutoResponse;
import com.padroes.framework.utils.PadraoException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface IProdutoService {
    List<ProdutoResponse> carregarProdutos();
    ProdutoResponse criarProduto(ProdutoRequest produtosRequestDom) throws PadraoException;
    boolean atualizarProduto(Long id, ProdutoRequest produtosRequestDom) throws PadraoException;
    void deletarProduto(Long id) throws PadraoException;
    ProdutoResponse carregarProdutoById(Long id) throws PadraoException;
}
