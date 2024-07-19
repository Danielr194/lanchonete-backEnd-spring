package com.lanchonete.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UsuarioCreateDto {

    @NotBlank
    @Size(min = 3)
    private String nome;

    @Email
    private String email;

    @CPF
    private String cpf;

    @NotBlank
    @Size(min = 9)
    private String telefone;

    private String senha;

    @Past
    private LocalDate dtNasc;

}
