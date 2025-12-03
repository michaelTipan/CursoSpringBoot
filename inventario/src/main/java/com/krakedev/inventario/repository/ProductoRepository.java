package com.krakedev.inventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krakedev.inventario.entity.EstadoProducto;
import com.krakedev.inventario.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    Optional<Producto> findByIdProducto(Long idProducto);
    Optional<Producto> findByNombreProducto(String nombreProducto);
    List<Producto> findByEstadoProducto(EstadoProducto estadoProducto);
}
