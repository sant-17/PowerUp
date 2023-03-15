package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String surname;

    @Column(name = "documento_identidad")
    private Long identificationNumber;

    @Column(name = "celular")
    private String phoneNumber;

    @Column(name = "correo")
    private String email;

    @Column(name = "clave")
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RoleEntity role;
}
