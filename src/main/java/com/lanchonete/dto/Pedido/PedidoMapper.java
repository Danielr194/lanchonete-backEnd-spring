package com.lanchonete.dto.Pedido;

import com.lanchonete.dto.usuario.UsuarioDto;
import com.lanchonete.entity.Pedido;
import com.lanchonete.entity.Usuario;

import java.util.List;

public class PedidoMapper {


    public static PedidoExibitionDto toDto(Pedido pedido){
        if (pedido == null)return null;
        return PedidoExibitionDto.builder().usuarioId(usuarioDto(pedido.getUsuarioId())).id(pedido.getId()).data(pedido.getData()).build();
    }

    public static List<PedidoExibitionDto> toDto(List<Pedido> pedidos){
        return pedidos.stream().map(PedidoMapper::toDto).toList();
    }

    public static UsuarioDto usuarioDto(Usuario usuario){
        return UsuarioDto.builder().id(usuario.getId()).email(usuario.getEmail()).nome(usuario.getNome()).build();
    }

    public static Pedido toEntity(PedidoCreateDto pedidoCreateDto){
        if (pedidoCreateDto == null)return null;
        return Pedido.builder().data(pedidoCreateDto.getData()).build();
    }
}
