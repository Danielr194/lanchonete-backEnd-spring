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
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public Usuario create(Usuario novoUsuario) {
        uniqueCpf(novoUsuario.getCpf());
        uniqueEmail(novoUsuario.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        Usuario usuarioSalvo = repository.save(novoUsuario);
        return usuarioSalvo;
    }

    public Usuario login(UsuarioLoginDto usuarioLogin) {
        Usuario usuario = this.repository.findByEmail(usuarioLogin.email()).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(400), "Senha ou email invalidos"));
        if (passwordEncoder.matches(usuarioLogin.senha(), usuario.getSenha())) {
            String token = this.tokenService.generateToken(usuario);
            return usuario;
        }
        // Fazer o tratamento de excecao
        throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Senha ou email invalidos");
    }

    public Usuario getUsuario(Long id){
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Usuario"));
        return usuario;
    }

    public Usuario update(Long id, UsuarioUpdateDto usuarioAtualizar){
        uniqueEmail(usuarioAtualizar.getEmail());
        Usuario usuarioBanco = getUsuario(id);
        Usuario usuarioMapeado = UsuarioMapper.toEdit(usuarioAtualizar, usuarioBanco);
        usuarioMapeado.setSenha(passwordEncoder.encode(usuarioMapeado.getSenha()));
        Usuario usuarioSalvo = repository.save(usuarioBanco);
        String token = this.tokenService.generateToken(usuarioSalvo);
        return usuarioBanco;
    }

    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ErroClienteException("Usuario");
        }
        repository.deleteById(id);
    }


    public boolean uniqueCpf(String cpf){
        Optional<Usuario> optUsuario = repository.findByCpf(cpf);
        if (optUsuario.isPresent()) throw new ConflitoException("CPF");
        return true;
    }

    public boolean uniqueEmail(String email){
        Optional<Usuario> optUsuario = repository.findByEmail(email);
        if (optUsuario.isPresent()) throw new ConflitoException("EMAIL");
        return true;
    }

}
