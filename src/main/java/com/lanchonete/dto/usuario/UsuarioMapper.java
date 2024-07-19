package com.lanchonete.dto.usuario;

import com.lanchonete.entity.Usuario;

public class UsuarioMapper {


    public static Usuario toEntity(UsuarioCreateDto dto){
        if (dto == null)return null;
        return Usuario.builder().cpf(dto.getCpf())
                .senha(dto.getSenha())
                .nome(dto.getNome())
                .dtNasc(dto.getDtNasc())
                .telefone(dto.getTelefone())
                .email(dto.getEmail()).build();
    }

    public static UsuarioExibitionDto toDto(Usuario entity, String token){
        if (entity == null)return null;
        return UsuarioExibitionDto.builder().id(entity.getId())
                .senha(token)
                .cpf(entity.getCpf())
                .dtNasc(entity.getDtNasc())
                .email(entity.getEmail())
                .nome(entity.getNome())
                .telefone(entity.getTelefone()).build();
    }

    public static UsuarioExibitionDto toDto(Usuario entity){
        if (entity == null)return null;
        return UsuarioExibitionDto.builder().id(entity.getId())
                .senha(entity.getSenha())
                .cpf(entity.getCpf())
                .dtNasc(entity.getDtNasc())
                .email(entity.getEmail())
                .nome(entity.getNome())
                .telefone(entity.getTelefone()).build();
    }

    public static Usuario toEdit(UsuarioUpdateDto dto, Usuario usuario){
        if (dto == null) return null;

         usuario.setSenha(dto.getSenha());
         usuario.setEmail(dto.getEmail());
         usuario.setTelefone(dto.getTelefone());
         return usuario;
    }


}
