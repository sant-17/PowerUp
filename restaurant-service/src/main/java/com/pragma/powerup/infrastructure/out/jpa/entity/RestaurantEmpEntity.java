package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "empleados_restaurante")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEmpEntity {

    @Id
    @Column(name = "id_usuario")
    private Long user;

    @Column(name = "id_restaurante")
    private Long restaurant;
}
