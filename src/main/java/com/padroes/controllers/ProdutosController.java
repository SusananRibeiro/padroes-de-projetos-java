package com.padroes.controllers;
import com.padroes.dtos.ProdutoRequest;
import com.padroes.dtos.ProdutoResponse;
import com.padroes.entities.repositories.ProdutoRepository;
import com.padroes.framework.annotions.LogRest;
import com.padroes.framework.utils.PadraoException;
import com.padroes.framework.utils.ResponseUtil;
import com.padroes.services.impl.ProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
    @Autowired
    private ProdutoServiceImpl produtoServiceImpl;
    private ProdutoRepository produtoRepository;

    @GetMapping(path = "/listarProdutos")
    @LogRest
    public ResponseEntity<List<ProdutoResponse>> carregarProdutos(){
        List<ProdutoResponse> saida = produtoServiceImpl.carregarProdutos();
        return ResponseEntity.ok(saida);
    }

    @GetMapping("/listarProdutos/{id}")
    @LogRest
    public ResponseEntity<ProdutoResponse> carregarProdutoById(@PathVariable Long id) throws PadraoException {
        return ResponseEntity.ok(produtoServiceImpl.carregarProdutoById(id));
    }

    @PostMapping("/cadastrarProduto")
    @LogRest
    public ResponseEntity<?> criarProduto (@RequestBody ProdutoRequest produtoRequest){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoServiceImpl.criarProduto(produtoRequest));
        } catch (PadraoException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMensagens()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @PutMapping("/atualizarProduto/{id}")
    @LogRest
    public ResponseEntity<?> atualizarProduto (@PathVariable Long id, @RequestBody ProdutoRequest produtoRequest){
        try {
            return ResponseEntity.ok(
                    produtoServiceImpl.atualizarProduto(id, produtoRequest));
        } catch (PadraoException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMensagens()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @DeleteMapping("/excluirProduto/{id}")
    @LogRest
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) throws PadraoException {
        produtoServiceImpl.deletarProduto(id);

        return ResponseEntity.ok(null);
    }

}
