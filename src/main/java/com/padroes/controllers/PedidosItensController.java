package com.padroes.controllers;
import com.padroes.dtos.PedidoItensRequest;
import com.padroes.dtos.PedidoItensResponse;
import com.padroes.framework.annotions.LogRest;
import com.padroes.framework.utils.PadraoException;
import com.padroes.framework.utils.ResponseUtil;
import com.padroes.services.impl.PedidoItensServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/pedidoItens")
public class PedidosItensController {

    @Autowired
    private PedidoItensServiceImpl pedidoItensService;
    @GetMapping("/listarPedidoItens")
    @LogRest
    public ResponseEntity<List<PedidoItensResponse>> carregarPedidosItens(){
        List<PedidoItensResponse> saida = pedidoItensService.carregarPedidosItens();
        return ResponseEntity.ok(saida);
    }

    @GetMapping("/listarPedidoItens/{id}")
    @LogRest
    public ResponseEntity<PedidoItensResponse> carregarPedidoItensById(@PathVariable Long id) throws PadraoException {
        return ResponseEntity.ok(pedidoItensService.carregarPedidoItensById(id));
    }

    @PostMapping("/cadastrarPedidoItens")
    @LogRest
    public ResponseEntity<?> criarPedidoItens (@RequestBody PedidoItensRequest pedidoItensRequest){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoItensService.criarPedidoItens(pedidoItensRequest));
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

    @PutMapping("/atualizarPedidoItens/{id}")
    @LogRest
    public ResponseEntity<?> atualizarPedidoItens (@PathVariable Long id, @RequestBody PedidoItensRequest pedidoItensRequest){
        try {
            return ResponseEntity.ok(
                    pedidoItensService.atualizarPedidoItens(id, pedidoItensRequest));
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

    @DeleteMapping("/excluirPedidoItens/{id}")
    @LogRest
    public ResponseEntity<Void> deletarPedidoItens(@PathVariable Long id) throws PadraoException {
        pedidoItensService.deletarPedidoItens(id);

        return ResponseEntity.ok(null);
    }

}
