package com.egg.catalogo_mercaderia.services;

import com.egg.catalogo_mercaderia.entity.Articulo;
import com.egg.catalogo_mercaderia.entity.Fabrica;
import com.egg.catalogo_mercaderia.exceptions.MiException;
import com.egg.catalogo_mercaderia.repository.ArticuloRepositorio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ArticuloServicio {

  @Autowired
  ArticuloRepositorio articuloRepositorio;

  private static final AtomicInteger secuenciaNroArticulo = new AtomicInteger(0);

  @PostConstruct // se ejecuta automáticamente después de que todas las dependencias han sido inyectadas por Spring
  private void inicializarSecuencia() {
    Integer maxNroArticulo = articuloRepositorio.encontrarMaxNroArticulo();
    if (maxNroArticulo != null) {
      secuenciaNroArticulo.set(maxNroArticulo);
    }
  }

  @Transactional
  public void crearArticulo(String nombreArticulo, String descripcionArticulo, Fabrica fabrica) throws MiException {
    validar(nombreArticulo, descripcionArticulo, fabrica);
    Articulo articulo = new Articulo(nombreArticulo, descripcionArticulo, fabrica);
    inicializarSecuencia();
    articulo.setNroArticulo(secuenciaNroArticulo.incrementAndGet());
    articuloRepositorio.save(articulo);
  }

  @Transactional(readOnly = true)
  public List<Articulo> listarArticulos(){
    return articuloRepositorio.findAll();
  }

  @Transactional(readOnly = true)
  public Articulo buscarPorId(UUID id) throws MiException {
    return articuloRepositorio.findById(id)
        .orElseThrow(() -> new MiException("No se encontró el artículo"));
  }

  @Transactional
  public void eliminarArticulo(UUID id){
    articuloRepositorio.deleteById(id);
  }

  private void validar(String nombreArticulo, String descripcionArticulo, Fabrica fabrica) throws MiException {
    if (nombreArticulo == null || nombreArticulo.trim().isEmpty()) {
      throw new MiException("El nombre del artículo no puede estar vacío");
    }

    if (descripcionArticulo == null || descripcionArticulo.trim().isEmpty()) {
      throw new MiException("La descripción del artículo no puede estar vacía");
    }

    if (fabrica == null) {
      throw new MiException("Debe especificar una fábrica para el artículo");
    }
  }



}
