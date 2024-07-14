package com.padroes.controllers;

import com.padroes.dtos.EnderecoRequest;
import com.padroes.dtos.EnderecoResponse;
import com.padroes.framework.utils.PadraoException;
import com.padroes.framework.utils.ResponseUtil;
import com.padroes.services.impl.EnderecoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.padroes.framework.annotions.LogRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @GetMapping("/carregar")
    @LogRest
    public ResponseEntity<List<EnderecoResponse>> carregarEnderecos(){
        List<EnderecoResponse> out = enderecoService.carregarEnderecos();

        return ResponseEntity.ok(out);
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<EnderecoResponse> carregarEnderecoById(@PathVariable Long id) throws PadraoException {
        return ResponseEntity.ok(enderecoService.carregarEnderecoById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarEndereco(@RequestBody EnderecoRequest endereco){
        try {
            EnderecoResponse out = enderecoService.criarEndereco(endereco);

            return ResponseEntity.status(HttpStatus.CREATED).body(out);
        } catch (PadraoException senac) {
            senac.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(senac.getMensagens()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoRequest endereco){
        try {
            EnderecoResponse out = enderecoService.atualizarEndereco(id, endereco);

            return ResponseEntity.ok(out);
        } catch (PadraoException senac) {
            senac.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(senac.getMensagens()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id){
        enderecoService.deletarEndereco(id);
        return ResponseEntity.ok(null);
    }
}
