package com.lanchonete.service;


import com.lanchonete.entity.Lanche;
import com.lanchonete.entity.Pedido;
import com.lanchonete.entity.PedidoLanche;
import com.lanchonete.exception.NaoEncontradoException;
import com.lanchonete.repository.PedidoLancheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoLancheService {

    private final PedidoLancheRepository repository;
    private final LancheService lancheService;
    private final PedidoService pedidoService;


    public PedidoLanche create(PedidoLanche pedidoLanche, Long idLanche, Long idPedido){
        Lanche lanche = lancheService.show(idLanche);
        Pedido pedido = pedidoService.getPedido(idPedido);
        pedidoLanche.setLancheId(lanche);
        pedidoLanche.setPedidoId(pedido);
        return repository.save(pedidoLanche);
    }


    public List<PedidoLanche> showPorPedidos(Long idPedido){
        Pedido pedido = pedidoService.getPedido(idPedido);
        List<PedidoLanche> pedidoLanches = repository.findByPedidoIdIs(pedido);
        return pedidoLanches;
    }

    public PedidoLanche show(Long idPedidoLanche){
        Optional<PedidoLanche> pedidoLanche = repository.findById(idPedidoLanche);
        pedidoLanche.orElseThrow(() -> new NaoEncontradoException("Pedido Lanche"));
        return pedidoLanche.get();
    }




}
