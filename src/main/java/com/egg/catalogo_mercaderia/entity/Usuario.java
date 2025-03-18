package com.egg.catalogo_mercaderia.entity;

import com.egg.catalogo_mercaderia.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id_usuario")
  private UUID idUsuario;

  @Column(length = 50, nullable = false)
  private String nombre;

  @Column(length = 50, nullable = false)
  private String apellido;

  @Column(unique = true, length = 100, nullable = false)
  private String email;

  @Column(length = 70, nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private Rol rol = Rol.USER; // Por defecto se crea tipo User

}
