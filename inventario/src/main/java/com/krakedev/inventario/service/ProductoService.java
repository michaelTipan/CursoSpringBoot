package com.krakedev.inventario.service;

import java.util.List;
import java.util.Optional;

import com.krakedev.inventario.entity.EstadoProducto;
import com.krakedev.inventario.entity.Producto;

public interface ProductoService {

    Producto registrarProducto(Producto producto);
    List<Producto> listarProductos();
    Optional<Producto> buscarPorNombre(String nombre);
    Optional<Producto> buscarPorId(Long idProducto);
    Producto actualizarProducto(Long idProducto, Producto producto);
    void eliminarProducto(Long idProducto);

    Producto cambiarEstaProducto (Long idProducto, EstadoProducto nuevoestadoProducto);
    List<Producto> obtenerPorEstadoProducto(EstadoProducto estadoProducto);

}
