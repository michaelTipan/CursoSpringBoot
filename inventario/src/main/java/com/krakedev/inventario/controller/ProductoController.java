package com.krakedev.inventario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.inventario.entity.EstadoProducto;
import com.krakedev.inventario.entity.Producto;
import com.krakedev.inventario.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor

public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> resgitrarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.registrarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> burcarPorNombre(@PathVariable String nombre) {
        Optional<Producto> producto = productoService.buscarPorNombre(nombre);
        return producto.isPresent() ? ResponseEntity.ok(producto.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> burcarPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);
        return producto.isPresent() ? ResponseEntity.ok(producto.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<?> actualizarProducto (@PathVariable Long idProducto, @RequestBody Producto producto){
        try{
            Producto productoActualizado = new Producto();
            productoActualizado.setNombreProducto(producto.getNombreProducto());
            productoActualizado.setDescripcion(producto.getDescripcion());
            productoActualizado.setPrecio(producto.getPrecio());
            productoActualizado.setCantidad(producto.getCantidad());
            productoActualizado.setEstadoProducto(producto.getEstadoProducto());

            Producto productoBBDD = productoService.actualizarProducto(idProducto, productoActualizado);
            return ResponseEntity.ok(productoBBDD);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto){
        try{
            productoService.eliminarProducto(idProducto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping("/estado/{idProducto}")
    public ResponseEntity<?> cambiarEstadoProducto(@PathVariable Long idProducto, @RequestBody EstadoProducto estadoProducto){
        try{
            Producto productoActualizado = productoService.cambiarEstaProducto(idProducto, estadoProducto);
            return ResponseEntity.ok(productoActualizado);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/estado/{estadoProducto}")
    public ResponseEntity<List<Producto>> listarProductosPorEstado(@PathVariable EstadoProducto estadoProducto){
        List<Producto> productos = productoService.obtenerPorEstadoProducto(estadoProducto);
        return ResponseEntity.ok(productos);
    }
}
