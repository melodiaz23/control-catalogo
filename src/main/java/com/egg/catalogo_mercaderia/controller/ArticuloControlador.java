package com.egg.catalogo_mercaderia.controller;

import com.egg.catalogo_mercaderia.services.ArticuloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articulo")
public class ArticuloControlador {

  @Autowired
  ArticuloServicio articuloServicio;

  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
  @GetMapping("/listar")
  public String listarArticulos(ModelMap modelo){
    modelo.put("titulo", "Listado de Artículos");
    return "listaarticulo.html";
  }




}
