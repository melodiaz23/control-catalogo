package com.egg.catalogo_mercaderia.repository;

import com.egg.catalogo_mercaderia.entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticuloRepositorio extends JpaRepository<Articulo, UUID> {
}
