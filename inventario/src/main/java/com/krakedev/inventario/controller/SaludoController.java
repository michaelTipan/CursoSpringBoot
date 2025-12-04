package com.krakedev.inventario.controller;

import org.springframework.web.bind.annotation.GetMapping;

/*
    Controlador REST: Esa una clase en Java que expone rutas HTTP (URLs) para que otros sistemas puedan enviarle
    peticiones y recibir respuestas en formato JSON.
*/

//@RestController // Indica que esta clase es un controlador REST
//@RequestMapping("/micontroller")
public class SaludoController {

    @GetMapping("/saludo") // Mapea las peticiones GET a /micontroller/saludo
    public String saludar() {
        return "Hola desde Spring Boot";
    }
}
