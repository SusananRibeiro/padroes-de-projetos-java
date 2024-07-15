package com.padroes.entities.repositories;
import com.padroes.entities.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PedidoItensProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p.nome FROM pedidos_itens pi2 \n" +
            "JOIN produtos p ON p.id = pi2.produtoId.id\n" +
            "WHERE pi2.produtoId = :id")
    public List<Produto> carregarProdutoByPedidosItensId(@Param("id") Long id);


}
