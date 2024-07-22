package com.lanchonete.repository;

import com.lanchonete.entity.Pedido;
import com.lanchonete.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioIdIs(Usuario idUsuario);
}
