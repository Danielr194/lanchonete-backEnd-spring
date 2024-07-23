package com.lanchonete.repository;

import com.lanchonete.entity.Pedido;
import com.lanchonete.entity.PedidoLanche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoLancheRepository extends JpaRepository<PedidoLanche, Long> {
    List<PedidoLanche> findByPedidoIdIs(Pedido pedido);
}
