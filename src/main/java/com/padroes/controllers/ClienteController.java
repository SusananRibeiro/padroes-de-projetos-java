package com.padroes.controllers;

import com.padroes.dtos.ClienteRequest;
import com.padroes.dtos.ClienteResponse;
import com.padroes.entities.repositories.ClienteRepository;
import com.padroes.framework.annotions.LogRest;
import com.padroes.framework.utils.PadraoException;
import com.padroes.framework.utils.ResponseUtil;
import com.padroes.services.impl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(path = "/listarClientes")
    @LogRest
    public ResponseEntity<List<ClienteResponse>> carregarClientes(){
        return ResponseEntity.ok(clienteService.carregarClietes());
    }

    @GetMapping("/listarClientes/{id}")
    @LogRest
    public ResponseEntity<ClienteResponse> carregarClienteById(@PathVariable Long id) throws PadraoException {
        return ResponseEntity.ok(clienteService.carregarClienteById(id));
    }

    @PostMapping("/cadastrarCliente")
    @LogRest
    public ResponseEntity<?> criarCliente (@RequestBody ClienteRequest clienteRequest){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criarCliente(clienteRequest));
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

    @PutMapping("/atualizarCliente/{id}")
    @LogRest
    public ResponseEntity<?> atualizarCliente (@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        try {
            return ResponseEntity.ok(clienteService.atualizarCliente(id, clienteRequest));
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

    @DeleteMapping("/excluirCliente/{id}")
    @LogRest
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);

        return ResponseEntity.ok(null);
    }
}

