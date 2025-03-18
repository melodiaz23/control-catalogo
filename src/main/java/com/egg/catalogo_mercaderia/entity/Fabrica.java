package com.egg.catalogo_mercaderia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fabricas")
public class Fabrica {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id_fabrica")
  private UUID idFabrica;

  @Column(name = "nombre_fabrica", nullable = false, length = 100)
  private String nombreFabrica;

  @OneToMany(mappedBy = "fabrica", fetch = FetchType.LAZY, orphanRemoval = true) // orphanRemoval = true
  private List<Articulo> articulos = new ArrayList<>();


}
