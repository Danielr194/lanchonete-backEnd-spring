package com.lanchonete.dto.usuario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {

    private Long id;

    private String nome;

    private String email;
}
