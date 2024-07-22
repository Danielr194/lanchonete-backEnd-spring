package com.lanchonete.dto.Pedido;


import com.lanchonete.entity.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PedidoExibitionDto {

    private Long id;

    private LocalDateTime data;

    private UsuarioDto usuarioId;


    @Data
    @Builder
    public static class UsuarioDto{

        private Long id;

        private String nome;

        private String email;
    }

}
