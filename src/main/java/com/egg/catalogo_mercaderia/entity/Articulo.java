package com.egg.catalogo_mercaderia.entity;


import jakarta.persistence.*;
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
@Table(name = "articulos")
public class Articulo {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id_articulo")
  private UUID idArticulo;

  @Column(name = "nro_articulo", unique = true, nullable = false)
  private Integer nroArticulo;

  @Column(name = "nombre_articulo", length = 100, nullable = false)
  private String nombreArticulo;

  @Column(name = "descripcion_articulo", length = 255, nullable = false)
  private String descripcionArticulo;

  @ManyToOne
  @JoinColumn(name = "id_fabrica")
  private Fabrica fabrica;

  @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Imagen> imagenList = new ArrayList<>();


}
