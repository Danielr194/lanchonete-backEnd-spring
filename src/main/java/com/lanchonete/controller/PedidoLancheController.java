package com.lanchonete.controller;


import com.lanchonete.dto.pedidoLanche.PedidoLancheCreateDto;
import com.lanchonete.dto.pedidoLanche.PedidoLancheExibitionDto;
import com.lanchonete.dto.pedidoLanche.PedidoLancheMapper;
import com.lanchonete.entity.PedidoLanche;
import com.lanchonete.service.PedidoLancheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidosLanches")
@RequiredArgsConstructor
public class PedidoLancheController {

    private final PedidoLancheService service;

    @PostMapping
    public ResponseEntity<PedidoLancheExibitionDto> create(PedidoLancheCreateDto dto){
        PedidoLanche pedidoLancheNovo = PedidoLancheMapper.toEntity(dto);
        PedidoLanche pedidoLanche = service.create(pedidoLancheNovo, dto.getLancheId(), dto.getPedidoId());
        URI uri = URI.create("/pedidosLanches/"+ pedidoLanche.getId());
        return ResponseEntity.created(uri).body(PedidoLancheMapper.toDto(pedidoLanche));
    }

    @GetMapping("buscarPorPedido/{id}")
    public ResponseEntity<List<PedidoLancheExibitionDto>> showPorPedidos(@PathVariable Long id){
        List<PedidoLanche> pedidoLanches = service.showPorPedidos(id);
        if(pedidoLanches.isEmpty())return ResponseEntity.noContent().build();
        return ResponseEntity.ok(PedidoLancheMapper.toDto(pedidoLanches));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoLancheExibitionDto> show(@PathVariable Long id){
        PedidoLanche pedidoLanche = service.show(id);
        return ResponseEntity.ok(PedidoLancheMapper.toDto(pedidoLanche));
    }


}
