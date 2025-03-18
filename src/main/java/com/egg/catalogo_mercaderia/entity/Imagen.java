package com.egg.catalogo_mercaderia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "contenido") // Evita imprimir contenido de la imagen en logs
@Table(name = "imagenes")
public class Imagen {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, length = 50)
  private String mime;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(columnDefinition = "LONGBLOB")
  private byte[] contenido;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "articulo_id", nullable = false)
  private Articulo articulo;

}
