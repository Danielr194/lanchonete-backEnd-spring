package com.lanchonete.dto.pedidoLanche;

import com.lanchonete.dto.Pedido.PedidoExibitionDto;
import com.lanchonete.dto.lanche.LancheExibitionDto;
import com.lanchonete.entity.Lanche;
import com.lanchonete.entity.Pedido;
import com.lanchonete.entity.PedidoLanche;

import java.util.List;

public class PedidoLancheMapper {

    public static PedidoLanche toEntity(PedidoLancheCreateDto pedidoLancheCreateDto){
        if (pedidoLancheCreateDto == null)return null;
        return PedidoLanche.builder().observacao(pedidoLancheCreateDto.getObservacao()).totalPreco(pedidoLancheCreateDto.getTotalPreco()).build();
    }


    public static PedidoLancheExibitionDto toDto(PedidoLanche pedidoLanche){
        if (pedidoLanche == null)return null;
        return PedidoLancheExibitionDto.builder().id(pedidoLanche.getId()).qtdLanches(pedidoLanche.getQtdLanches()).totalPreco(pedidoLanche.getTotalPreco()).observacao(pedidoLanche.getObservacao()).pedidoId(pedidoDto(pedidoLanche.getPedidoId())).lancheId(lancheDto(pedidoLanche.getLancheId())).build();
    }

    public static List<PedidoLancheExibitionDto> toDto(List<PedidoLanche> pedidoLanche){
        return pedidoLanche.stream().map(PedidoLancheMapper::toDto).toList();
    }

    public static PedidoExibitionDto pedidoDto(Pedido pedido){
        if (pedido == null)return null;
        return PedidoExibitionDto.builder().id(pedido.getId()).data(pedido.getData()).build();
    }


    public static LancheExibitionDto lancheDto(Lanche lanche){
        if (lanche == null)return null;

        return LancheExibitionDto.builder().id(lanche.getId())
                .nome(lanche.getNome())
                .preco(lanche.getPreco())
                .descricao(lanche.getDescricao()).build();
    }

}
