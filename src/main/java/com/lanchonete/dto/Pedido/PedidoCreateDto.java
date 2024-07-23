package com.lanchonete.dto.Pedido;

import com.lanchonete.dto.usuario.UsuarioDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PedidoCreateDto {

    private LocalDateTime data;

    private Long usuarioId;

}
