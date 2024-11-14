package com.poli.polisales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class indexController {

    @GetMapping("/")
    public String home(Model model) {
        // Añadimos el título
        model.addAttribute("title", "POLIsales");

        // Añadimos una lista de imágenes para el carrusel
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);

        return "index";  // Thymeleaf buscará un archivo "index.html" en la carpeta "templates"
    }

    /*@GetMapping("/publicaciones")
    public String publicaciones(Model model) {
        model.addAttribute("title", "Publicaciones");
        return "index";
    }*/

    @GetMapping("/crear")
    public String crearPublicacion(Model model) {
        model.addAttribute("title", "Crear Publicación");
        return "crearPublicacion";
    }

    @GetMapping("/miPerfil")
    public String miPerfil(Model model) {
        model.addAttribute("title", "Mi Perfil");
        return "miPerfil";
    }
}

