package com.lanchonete.controller;


import com.lanchonete.dto.Pedido.PedidoCreateDto;
import com.lanchonete.dto.Pedido.PedidoExibitionDto;
import com.lanchonete.dto.Pedido.PedidoMapper;
import com.lanchonete.entity.Pedido;
import com.lanchonete.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody PedidoCreateDto pedido){
        Pedido novoPedido = PedidoMapper.toEntity(pedido);
        Pedido pedidoSalvo = service.create(novoPedido,pedido.getUsuarioId());
        return ResponseEntity.created(null).body(pedidoSalvo);
    }

    @GetMapping("buscarPorUsuario/{id}")
    public ResponseEntity<List<PedidoExibitionDto>> showPedidos(@PathVariable Long id){
        List<Pedido> pedidos = service.getPedidos(id);
        if (pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(PedidoMapper.toDto(pedidos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoExibitionDto> show(@PathVariable Long id){
        Pedido pedido = service.getPedido(id);
        return ResponseEntity.ok(PedidoMapper.toDto(pedido));
    }

}
