package com.egg.catalogo_mercaderia.services;

import com.egg.catalogo_mercaderia.entity.Fabrica;
import com.egg.catalogo_mercaderia.exceptions.MiException;
import com.egg.catalogo_mercaderia.repository.FabricaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FabricaServicio {

  @Autowired
  FabricaRepositorio fabricaRepositorio;

  @Transactional
  public void crearFabrica(String nombreFabrica){
    Fabrica fabrica = new Fabrica(nombreFabrica);
    fabricaRepositorio.save(fabrica);
  }

  @Transactional(readOnly = true)
  public Fabrica buscarPorId(UUID id) throws MiException {
    return fabricaRepositorio.findById(id)
        .orElseThrow(() -> new MiException("No se encontró la fábrica"));
  }

  @Transactional(readOnly = true)
  public List<Fabrica> listarFabricas(){
    return fabricaRepositorio.findAll();
  }

  @Transactional
  public void eliminarFabrica(UUID id){
    fabricaRepositorio.deleteById(id);
  }

  private void validar(String nombreFabrica) throws MiException {
    if (nombreFabrica == null || nombreFabrica.trim().isEmpty()) {
      throw new MiException("El nombre de la fábrica no puede estar vacío");
    }
  }



}
