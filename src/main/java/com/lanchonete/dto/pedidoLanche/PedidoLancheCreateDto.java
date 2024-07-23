package com.lanchonete.dto.pedidoLanche;

import com.lanchonete.entity.Lanche;
import com.lanchonete.entity.Pedido;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoLancheCreateDto {

    private Long pedidoId;

    private Long lancheId;

    private Double totalPreco;

    private int qtdLanches;

    private String observacao;

}
