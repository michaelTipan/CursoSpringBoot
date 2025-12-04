package com.krakedev.inventario.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.inventario.entity.EstadoProducto;
import com.krakedev.inventario.entity.Producto;
import com.krakedev.inventario.repository.ProductoRepository;
import com.krakedev.inventario.service.ProductoService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    @Override
    public Producto registrarProducto(Producto producto) {
        // Producto nuevoProducto = productoRepository.save(producto);
        // return nuevoProducto;
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        // List<Producto> productos = productoRepository.findAll();
        // return productos;
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        // Optional<Producto> producto = productoRepository.findByNombreProducto(nombre);
        // return producto;
        return productoRepository.findByNombreProducto(nombre);
    }

    @Override
    public Optional<Producto> buscarPorId(Long idProducto) {
        return productoRepository.findByIdProducto(idProducto);
    }

    @Override
    @SneakyThrows
    public Producto actualizarProducto(Long idProducto, Producto producto){
        Producto productoExistente = productoRepository.findByIdProducto(idProducto)
                .orElseThrow(()-> new Exception("Producto con ID " + idProducto + " no encontrado"));

        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        productoExistente.setEstadoProducto(producto.getEstadoProducto());

        Producto productoActualizado = productoRepository.save(productoExistente);
        return productoActualizado;
    }

    @Override
    @SneakyThrows
    public void eliminarProducto(Long idProducto) {
        productoRepository.findByIdProducto(idProducto)
                .orElseThrow(()-> new Exception("Producto con ID " + idProducto + " no encontrado"));

        productoRepository.deleteById(idProducto);
    }

    @Override
    @SneakyThrows
    public Producto cambiarEstaProducto(Long idProducto, EstadoProducto nuevoestadoProducto) {
        Producto productoExistente = productoRepository.findByIdProducto(idProducto)
                .orElseThrow(()-> new Exception("Producto con ID " + idProducto + " no encontrado"));

        productoExistente.setEstadoProducto(nuevoestadoProducto);
        return productoRepository.save(productoExistente);
    }

    @Override
    public List<Producto> obtenerPorEstadoProducto(EstadoProducto estadoProducto) {
        return productoRepository.findByEstadoProducto(estadoProducto);
    }


}
