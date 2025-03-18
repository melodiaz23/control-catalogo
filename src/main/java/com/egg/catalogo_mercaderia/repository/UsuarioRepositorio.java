package com.egg.catalogo_mercaderia.repository;

import com.egg.catalogo_mercaderia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID> {

}
