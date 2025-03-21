package com.egg.catalogo_mercaderia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "articulos")
public class Articulo {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id_articulo")
  private UUID idArticulo;

  @Column(name = "nro_articulo", unique = true, nullable = false)
  private Integer nroArticulo;

  @NonNull
  @Column(name = "nombre_articulo", length = 100, nullable = false)
  private String nombreArticulo;

  @NonNull
  @Column(name = "descripcion_articulo", length = 255, nullable = false)
  private String descripcionArticulo;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "id_fabrica", nullable = false)
  private Fabrica fabrica;

  @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Imagen> imagenList = new ArrayList<>();


}
