package com.lanchonete.dto.lanche;

import com.lanchonete.entity.Lanche;

import java.util.List;

public class LancheMapper {

    public static LancheExibitionDto toDto(Lanche lanche){
        if (lanche == null)return null;

        return LancheExibitionDto.builder().id(lanche.getId())
                .nome(lanche.getNome())
                .preco(lanche.getPreco())
                .descricao(lanche.getDescricao()).build();
    }

    public static List<LancheExibitionDto> toDto(List<Lanche> lanches){
        return lanches.stream().map(LancheMapper::toDto).toList();
    }
}
