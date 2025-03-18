package com.egg.catalogo_mercaderia.repository;

import com.egg.catalogo_mercaderia.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, UUID> {
}
