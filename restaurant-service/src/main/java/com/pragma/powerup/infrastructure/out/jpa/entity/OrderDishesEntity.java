package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "platos_pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(OrderDishesId.class)
public class OrderDishesEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private OrderEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_plato", referencedColumnName = "id")
    private DishEntity dish;

    @Column(name = "cantidad")
    private Integer quantity;
}
