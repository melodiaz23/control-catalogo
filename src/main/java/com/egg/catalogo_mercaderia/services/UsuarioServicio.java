package com.egg.catalogo_mercaderia.services;

import com.egg.catalogo_mercaderia.entity.Usuario;
import com.egg.catalogo_mercaderia.exceptions.MiException;
import com.egg.catalogo_mercaderia.repository.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServicio implements UserDetailsService {

  @Autowired
  UsuarioRepositorio usuarioRepositorio;

  @Transactional
  public void registrar(String nombre, String apellido, String email, String password, String password2) throws MiException{
    validar(nombre, apellido, email, password, password2);
    Usuario usuario = new Usuario(nombre, apellido, email, new BCryptPasswordEncoder().encode(password));
    usuarioRepositorio.save(usuario);
  }

  @Transactional(readOnly = true)
  public List<Usuario> listarUsuarios(){
    return usuarioRepositorio.findAll();
  }

  @Transactional(readOnly = true)
  public Usuario buscarPorId(UUID id) throws MiException {
    return usuarioRepositorio.findById(id)
        .orElseThrow(()-> new MiException("No se encontró usuario con ese ID"));
  }

  @Transactional
  public Usuario actualizarUsuario(UUID id,String nombre, String apellido, String email, String password, String password2) throws MiException {
    validar(nombre, apellido, email, password, password2);
    Usuario usuario = buscarPorId(id);
    usuario.setNombre(nombre);
    usuario.setApellido(apellido);
    usuario.setEmail(email);
    if (password != null) {
      usuario.setPassword(new BCryptPasswordEncoder().encode(password));
    }
    return usuarioRepositorio.save(usuario);
  }

  @Transactional
  public void eliminarUsuario(UUID id){
    usuarioRepositorio.deleteById(id);
  }

  private void validar(String nombre, String apellido, String email, String password, String password2) throws MiException{
    if (nombre == null || nombre.isEmpty()) {
      throw new MiException("El nombre no puede ser nulo o estar vacío");
    }
    if (email == null || email.isEmpty()) {
      throw new MiException("El email no puede ser nulo o estar vacío");
    }
    if (usuarioRepositorio.buscarPorEmail(email) != null &&
        usuarioRepositorio.buscarPorEmail(email).getEmail().equals(email)) {
      throw new MiException("Usuario ya se encuentra registrado");
    }
    if (password == null || password.length() <= 5) {
      throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
    }
    if (!password.equals(password2)) {
      throw new MiException("Las contraseñas ingresadas deben ser iguales");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
    if (usuario!=null){
      List<GrantedAuthority> permisos = new ArrayList<>();
      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
      permisos.add(p);
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = attributes.getRequest().getSession(true);
      session.setAttribute("usuariosession", usuario);
      return new User(usuario.getEmail(), usuario.getPassword(), permisos);
    } else {
      return null;
    }

  }
}
