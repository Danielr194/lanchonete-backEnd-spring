package com.lanchonete.dto.usuario;

import lombok.Builder;

@Builder
public record UsuarioLoginDto(String email, String senha) {
}
