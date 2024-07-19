package com.lanchonete.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.exec.spi.JdbcOperationQueryInsert;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioId;

}
