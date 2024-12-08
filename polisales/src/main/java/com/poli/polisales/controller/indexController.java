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
        // Título
        model.addAttribute("title", "POLIsales");

        // Lista de imágenes para el carrusel
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);

        return "index";  
    }

    @GetMapping("/crear")
    public String crearPublicacion(Model model) {
        // Título
        model.addAttribute("title", "Crear Publicación");

        // Lista de imágenes para el carrusel
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);

        return "crearPublicacion";
    }

    @GetMapping("/miPerfil")
    public String miPerfil(Model model) {
        // Título
        model.addAttribute("title", "Mi Perfil");

        // Lista de imágenes para el carrusel
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);

        return "miPerfil";
    }
}