package com.lanchonete.dto.lanche;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LancheExibitionDto {

    private Long id;

    private String nome;

    private String descricao;

    private Double preco;
}
