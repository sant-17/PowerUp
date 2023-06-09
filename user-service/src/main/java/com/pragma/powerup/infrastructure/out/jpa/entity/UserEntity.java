package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

    @Column(name = "correo", unique = true)
    private String email;

    @Column(name = "clave")
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RoleEntity role;
}
