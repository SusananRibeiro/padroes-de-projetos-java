package com.padroes.dtos;

import com.padroes.framework.utils.enums.EstadosDoBrasil;
import lombok.Data;

@Data
public class EnderecoRequest {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private EstadosDoBrasil estado;
    private Integer cep;
    private String pais;
    private Long clienteId;

    // Não precisa gerar Getters, Setters e toString(), pois o "@Data" vai fazer isso

}
