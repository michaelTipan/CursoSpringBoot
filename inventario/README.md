# Curso de Spring Boot: De Cero a Experto

Este documento contiene el código fuente exacto desarrollado en cada video del curso. Úsalo como referencia para comparar tu progreso.

---

## Módulo 1: Introducción y Fundamentos
*(Videos teóricos, sin código)*

---

## Módulo 2: Configuración y Primeros Pasos

### Video: 04_Creacion_SaludoController
**Archivo**: `src/main/java/com/krakedev/inventario/controller/SaludoController.java`

```java
package com.krakedev.inventario.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/micontroller")
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar() {
        return "Hola desde Spring Boot";
    }
}
```

---

## Módulo 3: Construyendo una API REST (CRUD)

### Video: 06_Crear_entidad_mensaje
**Archivo**: `src/main/java/com/krakedev/inventario/entity/Message.java`

```java
package com.krakedev.inventario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int id;
    private String content;
}
```

### Video: 07_Listar_Mensajes
**Archivo**: `src/main/java/com/krakedev/inventario/controller/MessageController.java`
*(Configuración inicial y método GET)*

```java
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private List<Message> mensajes = new ArrayList<>();

    public MessageController() {
        mensajes.add(new Message(1, "Krake Dev Escuela de Programacion"));
        mensajes.add(new Message(2, "Spring Boot desde cero"));
    }

    @GetMapping
    public List<Message> listarMensajes() {
        return mensajes;
    }
    // ... otros métodos
}
```

### Video: 08_Listar_Mensajes_por_Id
**Archivo**: `src/main/java/com/krakedev/inventario/controller/MessageController.java`
*(Método GET por ID)*

```java
    // /mensajes/2
    @GetMapping("/{id}")
    public Message obtenerMensajePorId(@PathVariable int id) {
        Optional<Message> mensaje = mensajes.stream()
                .filter(m -> m.getId() == id).findFirst();

        return mensaje.orElse(null);
    }
```

### Video: 09_Crear_mensajes
**Archivo**: `src/main/java/com/krakedev/inventario/controller/MessageController.java`
*(Método POST)*

```java
    @PostMapping
    public Message crearMensaje(@RequestBody Message message) {
        mensajes.add(message);
        return message;
    }
```

### Video: 10_Eliminar_mensaje
**Archivo**: `src/main/java/com/krakedev/inventario/controller/MessageController.java`
*(Método DELETE)*

```java
    @DeleteMapping("/{id}")
    public void eliminarMensaje(@PathVariable int id){
        mensajes.removeIf(m -> m.getId() == id);
    }
```

---

## Módulo 4: Persistencia de Datos con MySQL

### Video: 12_Configuracion_archivo_ApplicationProperties
**Archivo**: `src/main/resources/application.properties`

```properties
spring.application.name=inventario
server.port=8082

#Configuracion de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/inventario
spring.datasource.username=root
spring.datasource.password=Krakedev.2025
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

#JPA y Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Video: 13_Crear_entidad_producto
**Archivo**: `src/main/java/com/krakedev/inventario/entity/Producto.java`

```java
package com.krakedev.inventario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto", nullable = false, length = 100)
    private String nombreProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoProducto estadoProducto;
}
```
