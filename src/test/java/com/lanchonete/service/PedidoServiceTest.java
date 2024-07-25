package com.lanchonete.service;

import com.lanchonete.entity.Pedido;
import com.lanchonete.entity.Usuario;
import com.lanchonete.exception.NaoEncontradoException;
import com.lanchonete.repository.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste do service")
@ActiveProfiles("test")
class PedidoServiceTest {

    @Autowired
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private UsuarioService usuarioService;


    @Test
    @DisplayName("Criar PedidoLanche com sucesso")
    void cenarioCriacaoComSucesso(){
        Usuario usuario = Usuario.builder().id(1L).senha("Daniel@19").email("dlnsantos19@gmail.com")
                .nome("Daniel Rocha dos Santos")
                .cpf("44581975840")
                .dtNasc(LocalDate.of(2004, 8, 19))
                .telefone("11 9872536125").build();

        Pedido pedidoNovo = Pedido.builder().data(LocalDateTime.now()).build();
        Pedido pedidoSalvo = Pedido.builder().id(1L).usuarioId(usuario).data(LocalDateTime.now()).build();
        when(usuarioService.getUsuario(usuario.getId())).thenReturn(usuario);
        pedidoNovo.setUsuarioId(usuario);
        when(pedidoRepository.save(pedidoNovo)).thenReturn(pedidoSalvo);

        Pedido pedido = pedidoService.create(pedidoNovo, usuario.getId());
        assertNotNull(pedido.getId());
        assertNotNull(pedido.getUsuarioId());
        assertEquals(pedidoSalvo, pedido);
        verify(usuarioService, times(1)).getUsuario(any());
        verify(pedidoRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("buscar um pedido pelo idPedido com sucesso")
    void cenarioBuscarPorIdPedidoComSucesso(){
        Long idPedido = 1L;
        Pedido pedido = Pedido.builder().id(1L).usuarioId(new Usuario()).data(LocalDateTime.now()).build();
        when(pedidoRepository.findById(idPedido)).thenReturn(Optional.of(pedido));
        Pedido pedidoTest = pedidoService.getPedido(idPedido);

        assertEquals(pedido, pedidoTest);
        verify(pedidoRepository, times(1)).findById(idPedido);
    }

    @Test
    @DisplayName("buscar um pedido pelo idPedido com erro")
    void cenarioBuscarPorIdPedidoComErro(){
        Long idPedido = 1L;
        when(pedidoRepository.findById(idPedido)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> pedidoService.getPedido(idPedido));
        verify(pedidoRepository, times(1)).findById(idPedido);
    }

    @Test
    @DisplayName("buscar um pedido pelo idUsuario com sucesso")
    void cenarioBuscarPorIdUsuarioComSucesso(){
        Usuario usuario = Usuario.builder().id(1L).senha("Daniel@19").email("dlnsantos19@gmail.com")
                .nome("Daniel Rocha dos Santos")
                .cpf("44581975840")
                .dtNasc(LocalDate.of(2004, 8, 19))
                .telefone("11 9872536125").build();
        Pedido pedido = Pedido.builder().id(1L).usuarioId(usuario).data(LocalDateTime.now()).build();
        Pedido pedido2 = Pedido.builder().id(2L).usuarioId(usuario).data(LocalDateTime.now()).build();
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);
        pedidos.add(pedido2);
        when(usuarioService.getUsuario(usuario.getId())).thenReturn(usuario);
        when(pedidoRepository.findByUsuarioIdIs(usuario)).thenReturn(pedidos);
        List<Pedido> pedidoTest = pedidoService.getPedidos(usuario.getId());

        assertEquals(pedidos, pedidoTest);
        verify(usuarioService, times(1)).getUsuario(usuario.getId());
        verify(pedidoRepository, times(1)).findByUsuarioIdIs(usuario);
    }




}