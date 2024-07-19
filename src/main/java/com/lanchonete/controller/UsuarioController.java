package com.lanchonete.controller;


import com.lanchonete.dto.usuario.*;
import com.lanchonete.entity.Usuario;
import com.lanchonete.infra.security.TokenService;
import com.lanchonete.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioExibitionLoginDto> login(@RequestBody UsuarioLoginDto loginDto){
        Usuario usuario = usuarioService.login(loginDto);
        String token = this.tokenService.generateToken(usuario);
        return ResponseEntity.ok(new UsuarioExibitionLoginDto(usuario.getEmail(),token));
    }

    @PostMapping
    public ResponseEntity<UsuarioExibitionDto> create(@RequestBody @Valid UsuarioCreateDto dto){
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario usuarioSalvo = usuarioService.create(usuario);
        URI uri = URI.create("/usuarios/" + usuarioSalvo.getId());
        String token = this.tokenService.generateToken(usuarioSalvo);
        return ResponseEntity.created(uri).body(UsuarioMapper.toDto(usuarioSalvo, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibitionDto> showUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.getUsuario(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioExibitionDto> update(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateDto dto){
        Usuario usuario = usuarioService.update(id, dto);
        String token = this.tokenService.generateToken(usuario);
        return ResponseEntity.ok(UsuarioMapper.toDto(usuario, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
