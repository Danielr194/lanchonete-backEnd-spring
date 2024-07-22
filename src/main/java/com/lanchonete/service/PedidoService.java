package com.lanchonete.service;


import com.lanchonete.entity.Pedido;
import com.lanchonete.entity.Usuario;
import com.lanchonete.exception.NaoEncontradoException;
import com.lanchonete.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;

    public Pedido create(Pedido pedido, Long idUsuario){
        Usuario usuario = usuarioService.getUsuario(idUsuario);
        pedido.setUsuarioId(usuario);
        pedido.setData(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public Pedido getPedido(Long id){
        Optional<Pedido> optPedido = pedidoRepository.findById(id);
        optPedido.orElseThrow(() -> new NaoEncontradoException("Pedido"));
        return optPedido.get();
    }

    public List<Pedido> getPedidos(Long idUsuario){

        List<Pedido> pedidos = pedidoRepository.findByUsuarioIdIs(usuarioService.getUsuario(idUsuario));
        return pedidos;
    }

}
