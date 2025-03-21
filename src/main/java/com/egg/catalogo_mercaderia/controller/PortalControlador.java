package com.egg.catalogo_mercaderia.controller;

import com.egg.catalogo_mercaderia.entity.Usuario;
import com.egg.catalogo_mercaderia.exceptions.MiException;
import com.egg.catalogo_mercaderia.services.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

  @Autowired
  UsuarioServicio usuarioServicio;

  @GetMapping("/")  // Se realiza el mapeo
  public String index() {
    return "index.html";
  }

  @GetMapping("/registro-usuario")
  public String registrar(ModelMap modelo){
    modelo.addAttribute("titulo", "Registro de Usuario");
    return "registrousuario.html";
  }

  @PostMapping("/registro-usuario")
  public String registroUsuario(@RequestParam String nombre,
                                @RequestParam String apellido,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String password2,
                                ModelMap modelo){
    try {
      usuarioServicio.registrar(nombre, apellido, email, password, password2);
      modelo.put("exito", "Usuario registrado con éxito");
      return "redirect:/inicio.html";
    } catch (MiException e) {
      modelo.put("error", e.getMessage());
      modelo.put("nombre", nombre);
      modelo.put("apellido", apellido);
      modelo.put("email", email);
      return "/registrousuario.html";
    }
  }

  @GetMapping("/login")
  public String ingresoUsuario(@RequestParam(required = false) String error, // Param is optional
                               ModelMap modelo) {
    if (error!=null){
      modelo.addAttribute("error", "Usuario o contraseña incorrectos");
    }
    modelo.addAttribute("titulo", "Ingreso Usuario");
    return "login.html";
  }

  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
  @GetMapping("/inicio")
  public String inicio(ModelMap modelo, HttpSession session) {
    Usuario datosUsuario = (Usuario) session.getAttribute("usuariosession");
    modelo.addAttribute("usuariosession", datosUsuario);
    modelo.addAttribute("titulo", "Bienvenido a La Casa de Electricidad");
    return "inicio.html";
  }

}
