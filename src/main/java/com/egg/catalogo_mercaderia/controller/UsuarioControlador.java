package com.egg.catalogo_mercaderia.controller;

import com.egg.catalogo_mercaderia.entity.Usuario;
import com.egg.catalogo_mercaderia.exceptions.MiException;
import com.egg.catalogo_mercaderia.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/perfil")
public class UsuarioControlador {

  @Autowired
  UsuarioServicio usuarioServicio;

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @GetMapping("/modificar/{id}")
  public String actualizar (@PathVariable("id") UUID id,
                            @RequestParam String nombre,
                            @RequestParam String apellido,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String password2,
                            ModelMap modelo){
    try {
      Usuario usuario = usuarioServicio.actualizarUsuario(id, nombre, apellido, email, password, password2);
      modelo.addAttribute("exito", "Usuario modificado con éxito");
      return "redirect:/inicio.html";
    } catch (MiException e) {
      modelo.addAttribute("error", e.getMessage());
      modelo.addAttribute("nombre", nombre);
      modelo.addAttribute("apellido", apellido);
      modelo.addAttribute("email", email);
      return "/modificar";
    }

  }


}
