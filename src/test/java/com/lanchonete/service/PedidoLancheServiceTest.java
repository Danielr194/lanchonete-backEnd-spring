package com.lanchonete.service;

import com.lanchonete.entity.Lanche;
import com.lanchonete.entity.Pedido;
import com.lanchonete.entity.PedidoLanche;
import com.lanchonete.exception.NaoEncontradoException;
import com.lanchonete.repository.PedidoLancheRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Teste do service")
@ActiveProfiles("test")
class PedidoLancheServiceTest {


    @Autowired
    @InjectMocks
    private PedidoLancheService pedidoLancheService;

    @Mock
    private PedidoLancheRepository repository;
    @Mock
    private LancheService lancheService;
    @Mock
    private PedidoService pedidoService;


    @Test
    @DisplayName("Criar PedidoLanche com sucesso")
    void cenarioCriarSucesso(){
        Long idPedido = 2L;
        Long idLanche = 3L;
        Pedido pedido = Pedido.builder().id(2L).build();
        Lanche lanche = Lanche.builder().id(3L).build();
        PedidoLanche novoPedido = PedidoLanche.builder().qtdLanches(2).observacao("Sem cebola").totalPreco(34.6).build();
        PedidoLanche pedidoSalvo = PedidoLanche.builder().id(1L).qtdLanches(2).observacao("Sem cebola").pedidoId(pedido).lancheId(lanche).totalPreco(34.6).build();
        when(pedidoService.getPedido(idPedido)).thenReturn(pedido);
        when(lancheService.show(idLanche)).thenReturn(lanche);
        novoPedido.setLancheId(lanche);
        novoPedido.setPedidoId(pedido);
        when(repository.save(novoPedido)).thenReturn(pedidoSalvo);

        PedidoLanche pedidoTest = pedidoLancheService.create(novoPedido, idLanche, idPedido);

        assertEquals(pedidoSalvo, pedidoTest);
        assertNotNull(pedidoTest.getId());
        verify(pedidoService, times(1)).getPedido(any());
        verify(lancheService, times(1)).show(any());
        verify(repository, times(1)).save(novoPedido);
    }


    @Test
    @DisplayName("buscar um pedidosLanche pelo idPedidoLanche com sucesso")
    void cenarioBuscarComSucesso(){
        Long idPedidoLanche = 1L;
        Pedido pedido = Pedido.builder().id(2L).build();
        Lanche lanche = Lanche.builder().id(3L).build();
        PedidoLanche pedidoLanche = PedidoLanche.builder().id(1L).qtdLanches(2).observacao("Sem cebola").pedidoId(pedido).lancheId(lanche).totalPreco(34.6).build();
        when(repository.findById(idPedidoLanche)).thenReturn(Optional.of(pedidoLanche));
        PedidoLanche pedidoLancheTest = pedidoLancheService.show(idPedidoLanche);

        assertEquals(pedidoLanche, pedidoLancheTest);
        verify(repository, times(1)).findById(idPedidoLanche);

    }

    @Test
    @DisplayName("buscar uma lista de pedidosLanche pelo idPedidoLanche com erro")
    void cenarioBuscarComErro(){
        Long idPedidoLanche = 1L;
        Pedido pedido = Pedido.builder().id(2L).build();
        Lanche lanche = Lanche.builder().id(3L).build();
        PedidoLanche pedidoLanche = PedidoLanche.builder().id(1L).qtdLanches(2).observacao("Sem cebola").pedidoId(pedido).lancheId(lanche).totalPreco(34.6).build();
        when(repository.findById(idPedidoLanche)).thenReturn(Optional.empty());
        assertThrows(NaoEncontradoException.class, () -> pedidoLancheService.show(idPedidoLanche));

        verify(repository, times(1)).findById(idPedidoLanche);
    }
}