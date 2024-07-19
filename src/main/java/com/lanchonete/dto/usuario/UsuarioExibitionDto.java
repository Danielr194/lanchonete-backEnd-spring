package com.lanchonete.dto.usuario;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class UsuarioExibitionDto {

    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    private String senha;

    private LocalDate dtNasc;

}
