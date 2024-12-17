package com.poli.polisales.controller;

import com.poli.polisales.model.Publicacion;
import com.poli.polisales.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private PublicacionService publicacionService; // Inyecta el servicio

    @GetMapping("/")
    public String home(Model model) {
        // Título de la página
        model.addAttribute("title", "POLIsales");

        // Lista de imágenes para el carrusel
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);

        // Obtener todas las publicaciones desde la base de datos
        List<Publicacion> publicaciones = publicacionService.findAll();
        model.addAttribute("publicaciones", publicaciones);

        return "index";  
    }

    @GetMapping("/crear")
    public String crearPublicacion(Model model) {
        model.addAttribute("title", "Crear Publicación");
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);
        return "crearPublicacion";
    }

    @GetMapping("/miPerfil")
    public String miPerfil(Model model) {
        model.addAttribute("title", "Mi Perfil");
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);
        return "miPerfil";
    }

   /*  @GetMapping("/misPublicaciones")
    public String misPublicaciones(Model model) {
        model.addAttribute("title", "Mis Publicaciones");
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);
        return "misPublicaciones";
    }*/

    @GetMapping("/misPublicaciones")
    public String  misPublicaciones(Model model) {
        model.addAttribute("title", "Mis Publicaciones");

        // Lista de imágenes para el carrusel
        List<String> carouselImages = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
        model.addAttribute("carouselImages", carouselImages);

        // Obtener todas las publicaciones desde la base de datos
        List<Publicacion> publicaciones = publicacionService.findAll();
        model.addAttribute("publicaciones", publicaciones);

        return "misPublicaciones";  
    }
}