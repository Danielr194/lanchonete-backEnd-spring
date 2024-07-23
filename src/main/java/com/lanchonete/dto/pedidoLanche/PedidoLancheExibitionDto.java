package com.lanchonete.dto.pedidoLanche;


import com.lanchonete.dto.Pedido.PedidoExibitionDto;
import com.lanchonete.dto.lanche.LancheExibitionDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoLancheExibitionDto {

    private Long id;

    private PedidoExibitionDto pedidoId;

    private LancheExibitionDto lancheId;

    private Double totalPreco;

    private int qtdLanches;

    private String observacao;

}
