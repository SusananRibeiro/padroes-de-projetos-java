package com.padroes.mappers;
import com.padroes.dtos.PedidoPedidoItensResponse;
import com.padroes.dtos.PedidoRequest;
import com.padroes.dtos.PedidoResponse;
import com.padroes.entities.models.Cliente;
import com.padroes.entities.models.Endereco;
import com.padroes.entities.models.Pedido;
import com.padroes.entities.models.PedidoItens;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static Pedido pedidoResquestParaPedido(PedidoRequest pedidoRequest, Cliente cliente, Endereco endereco){
        Pedido saida = new Pedido();
        saida.setDataCriacao(LocalDateTime.now());
        saida.setDataEntrega(calcularDataDeEntrega(LocalDateTime.now())); // chamar o método de calculo da data de entrega aqui
        saida.setValorDesconto(pedidoRequest.getValorDesconto());
        saida.setClienteId(cliente);
        saida.setEnderecoId(endereco);
        return saida;
    }

    public static PedidoResponse pedidoParaPedidoResponse(Pedido pedido){
        PedidoResponse saida = new PedidoResponse();
        saida.setId(pedido.getId());
        saida.setDataCriacao(pedido.getDataCriacao());
        saida.setDataEntrega(pedido.getDataEntrega());
        saida.setValorDesconto(pedido.getValorDesconto());
        saida.setClienteId(pedido.getClienteId().getId());
        saida.setEnderecoId(pedido.getEnderecoId().getId());
        saida.getValorTotal();

        return saida;
    }

    public static PedidoResponse pedidoParaPedidoProdutoResponse(Pedido pedido,
                                                                 List<PedidoItens> pedidoItens){
        PedidoResponse out = PedidoMapper.pedidoParaPedidoResponse(pedido);
        List<PedidoPedidoItensResponse> pedidosPedidosItensResponseList = pedidoItens.stream()
                .map(PedidoMapper::pedidoItensParaPedidoProdutoResponse)
                .collect(Collectors.toList());
        out.setPedidosItens(pedidosPedidosItensResponseList);
        return out;
    }

    public static PedidoPedidoItensResponse pedidoItensParaPedidoProdutoResponse(PedidoItens pedidosItens){
        PedidoPedidoItensResponse saida = new PedidoPedidoItensResponse();
        saida.setId(pedidosItens.getId());
        saida.setQuantidade(pedidosItens.getQuantidade());
        saida.setValorUnitario(pedidosItens.getValorUnitario());
        saida.setProdutoId(pedidosItens.getProdutoId().getId());
        saida.setPedidoId(pedidosItens.getPedidoId().getId());
        saida.setNomeDoProduto(pedidosItens.getProdutoId().getNome());
        return saida;
    }

    // Calculo da data de entrega
    public static LocalDate calcularDataDeEntrega(LocalDateTime dataCriacao) {
        // Adiciona 15 dias à data de criação
        LocalDateTime dataEntrega = dataCriacao.plus(15, ChronoUnit.DAYS);

        // Extrai a parte da data (ignorando a parte do tempo)
        return dataEntrega.toLocalDate();
    }

}