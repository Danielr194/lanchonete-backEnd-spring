package com.lanchonete.service;

import com.lanchonete.dto.usuario.UsuarioLoginDto;
import com.lanchonete.dto.usuario.UsuarioMapper;
import com.lanchonete.dto.usuario.UsuarioUpdateDto;
import com.lanchonete.entity.Usuario;
import com.lanchonete.exception.ConflitoException;
import com.lanchonete.exception.ErroClienteException;
import com.lanchonete.exception.NaoEncontradoException;
import com.lanchonete.infra.security.TokenService;
import com.lanchonete.repository.UsuarioRepository;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste do service")
@ActiveProfiles("test")
class UsuarioServiceTest {


    @Autowired
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private  UsuarioRepository repository;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Mock
    private  TokenService tokenService;


    @Test
    @DisplayName("Criar usuario com sucesso")
    void cenarioCriarSucesso(){
        Usuario novoUsuario = Usuario.builder().senha("Daniel@19").email("dlnsantos19@gmail.com")
        .nome("Daniel Rocha dos Santos")
        .cpf("44581975840")
        .dtNasc(LocalDate.of(2004, 8, 19))
        .telefone("11 9872536125").build();

        Usuario usuarioSalvo = Usuario.builder().id(1L).senha("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJMYW5jaG9uZXRlLWJhY2tlbmQiLCJzdWIiOiJkYW5pZWxyQGdtYWlsLmNvbSIsImV4cCI6MTcyMTI3NjUxN30.xjSrT-G-32IYjm_U8YlXCFgDX9pGAHDyKPGZENUKQfU").email("dlnsantos19@gmail.com")
                .nome("Daniel Rocha dos Santos")
                .cpf("44581975840")
                .dtNasc(LocalDate.of(2004, 8, 19))
                .telefone("11 9872536125").build();

        usuarioService.uniqueEmail(novoUsuario.getEmail());
        usuarioService.uniqueCpf(novoUsuario.getCpf());
        when(repository.findByEmail(novoUsuario.getEmail())).thenReturn(Optional.empty());
        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        when(repository.save(novoUsuario)).thenReturn(usuarioSalvo);

        Usuario usuario = usuarioService.create(novoUsuario);

        assertNotNull(usuario.getId());
        assertEquals(usuario.getCpf(), novoUsuario.getCpf());

        verify(repository, times(1)).save(novoUsuario);
        verify(repository, times(2)).findByEmail(any());
    }

//    @Test
//    @DisplayName("Erro ao criar o usuario")
//    void cenarioErroCriar(){
//        Usuario novoUsuario = Usuario.builder().senha("Daniel@19").email("dlnsantos19@gmail.com")
//                .nome("Daniel Rocha dos Santos")
//                .cpf("44581975840")
//                .dtNasc(LocalDate.of(2004, 8, 19))
//                .telefone("11 9872536125").build();
//
//        Usuario usuarioSalvo = Usuario.builder().id(1L).senha("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJMYW5jaG9uZXRlLWJhY2tlbmQiLCJzdWIiOiJkYW5pZWxyQGdtYWlsLmNvbSIsImV4cCI6MTcyMTI3NjUxN30.xjSrT-G-32IYjm_U8YlXCFgDX9pGAHDyKPGZENUKQfU").email("dlnsantos19@gmail.com")
//                .nome("Daniel Rocha dos Santos")
//                .cpf("44581975840")
//                .dtNasc(LocalDate.of(2004, 8, 19))
//                .telefone("11 9872536125").build();
//        when(usuarioService.uniqueCpf(novoUsuario.getCpf())).thenReturn(ConflitoException.class);
//        assertThrows(ConflitoException.class, () -> usuarioService.create(novoUsuario));
//
//    }

    @Test
    @DisplayName("Atualizar usuario com sucesso")
    void updateSucesso() {
        Long id = 1L;
        UsuarioUpdateDto usuarioEdit = UsuarioUpdateDto.builder().senha("Daniel@11").email("dlnsantos1@gmail.com")
                .telefone("11 9872716125").build();

        Usuario usuarioSalvo = Usuario.builder().id(1L).senha("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJMYW5jaG9uZXRlLWJhY2tlbmQiLCJzdWIiOiJkYW5pZWxyQGdtYWlsLmNvbSIsImV4cCI6MTcyMTI3NjUxN30.xjSrT-G-32IYjm_U8YlXCFgDX9pGAHDyKPGZENUKQfU").email("dlnsantos19@gmail.com")
                .nome("Daniel Rocha dos Santos")
                .cpf("44581975840")
                .dtNasc(LocalDate.of(2004, 8, 19))
                .telefone("11 9872536125").build();

        Usuario usuarioAtualizado = Usuario.builder().id(1L).senha("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJMYW5jaG9uZXRlLWJhY2tlbmQiLCJzdWIiOiJkYW5pZWxyQGdtYWlsLmNvbSIsImV4cCI6MTcyMTI3ODcxNn0.fXcvv9SeuMVOG1Cula7C7ga9SDjhnWLH82UAnUVHh7E").email("dlnsantos1@gmail.com")
                .nome("Daniel Rocha dos Santos")
                .cpf("44581975840")
                .dtNasc(LocalDate.of(2004, 8, 19))
                .telefone("11 9872716125").build();

        when(repository.findById(id)).thenReturn(Optional.of(usuarioSalvo));
        Usuario usuarioMapeado = UsuarioMapper.toEdit(usuarioEdit, usuarioSalvo);
        when(repository.save(usuarioMapeado)).thenReturn(usuarioAtualizado);

        Usuario usuario = usuarioService.update(id, usuarioEdit);

        assertNotNull(usuario.getId());
        assertEquals(usuario.getId(), id);
        assertEquals(usuario.getTelefone(), usuarioEdit.getTelefone());
        assertEquals(usuario.getEmail(), usuarioEdit.getEmail());

        verify(repository, times(1)).save(usuarioMapeado);
        verify(repository, times(1)).findById(id);
    }


    @Test
    @DisplayName("Buscar usuario por id com sucesso")
    void buscarSucesso(){
        Long id = 1L;
        Usuario usuario = Usuario.builder().id(1L).senha("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJMYW5jaG9uZXRlLWJhY2tlbmQiLCJzdWIiOiJkYW5pZWxyQGdtYWlsLmNvbSIsImV4cCI6MTcyMTI3ODcxNn0.fXcvv9SeuMVOG1Cula7C7ga9SDjhnWLH82UAnUVHh7E").email("dlnsantos1@gmail.com")
                .nome("Daniel Rocha dos Santos")
                .cpf("44581975840")
                .dtNasc(LocalDate.of(2004, 8, 19))
                .telefone("11 9872716125").build();


        when(repository.findById(id)).thenReturn(Optional.of(usuario));
        Usuario usuarioTeste = usuarioService.getUsuario(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(0)).existsById(id);
    }

    @Test
    @DisplayName("Buscar usuario por id com erro")
    void buscarUsuarioErro(){
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> usuarioService.getUsuario(id));

        verify(repository, times(1)).findById(id);
        verify(repository, times(0)).existsById(id);
    }


    @Test
    @DisplayName("Deletar um usuario por id com sucesso")
    void deleterSucesso() throws BadRequestException {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(Boolean.TRUE);

        usuarioService.delete(id);
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);

    }


    @Test
    @DisplayName("Deletar um usuario por id com erro")
    void deleterUsuarioErro() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(Boolean.FALSE);

        assertThrows(ErroClienteException.class, () -> usuarioService.delete(id));
        verify(repository, times(1)).existsById(id);
    }

}