package com.lanchonete.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UsuarioUpdateDto {

    @Email
    private String email;

    @NotBlank
    @Size(min = 9)
    private String telefone;

    @NotBlank
    private String senha;

}
