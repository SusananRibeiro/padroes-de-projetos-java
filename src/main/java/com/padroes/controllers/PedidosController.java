package com.padroes.controllers;
import com.padroes.dtos.PedidoRequest;
import com.padroes.dtos.PedidoResponse;
import com.padroes.framework.annotions.LogRest;
import com.padroes.framework.utils.PadraoException;
import com.padroes.framework.utils.ResponseUtil;
import com.padroes.services.impl.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private PedidoServiceImpl pedidosServiceImpl;

    @GetMapping("/listarPedidos")
    @LogRest
    public ResponseEntity<List<PedidoResponse>> carregarPedidos(){
        List<PedidoResponse> saida = pedidosServiceImpl.carregarPedidos();
        return ResponseEntity.ok(saida);
    }

    @GetMapping("/listarPedidos/{id}")
    @LogRest
    public ResponseEntity<PedidoResponse> carregarPedidoById(@PathVariable Long id) throws PadraoException {
            return ResponseEntity.ok(pedidosServiceImpl.carregarPedidoById(id));
    }

    @PostMapping("/cadastrarPedido")
    @LogRest
    public ResponseEntity<?> criarPedido (@RequestBody PedidoRequest pedidoRequest){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosServiceImpl.criarPedido(pedidoRequest));
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

    @PutMapping("/atualizarPedido/{id}")
    @LogRest
    public ResponseEntity<?> atualizarPedido (@PathVariable Long id, @RequestBody PedidoRequest pedidoRequest){
        try {
            return ResponseEntity.ok(
                    pedidosServiceImpl.atualizarPedido(id, pedidoRequest));
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

    @DeleteMapping("/excluirPedido/{id}")
    @LogRest
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id){
        pedidosServiceImpl.deletarPedido(id);
        return ResponseEntity.ok(null);
    }

}
