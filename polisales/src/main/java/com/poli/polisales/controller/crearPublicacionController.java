package com.poli.polisales.controller;

import com.poli.polisales.model.Publicacion;
import com.poli.polisales.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/publicaciones")
public class crearPublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    /**
     * Muestra el formulario de creación de publicaciones.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return La plantilla HTML para crear publicaciones.
     */
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        // Lista de categorías como simples cadenas
        List<String> categorias = Arrays.asList("Electrónica", "Muebles", "Libros", "Ropa", "Servicios");
        
        // Agregar atributos necesarios al modelo
        model.addAttribute("categorias", categorias); // Para poblar el dropdown en la vista
        model.addAttribute("publicacion", new Publicacion());
        return "crearPublicacion"; // Nombre de la vista HTML
    }

    /**
     * Procesa el formulario de creación de publicaciones.
     *
     * @param publicacion Objeto Publicacion enlazado al formulario.
     * @param imagen Archivo de imagen (opcional).
     * @return Redirección a la lista de publicaciones o página de éxito.
     */
    @PostMapping("/guardar")
    public String guardarPublicacion(
            @ModelAttribute Publicacion publicacion,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        try {
            // Asignar la fecha de publicación automáticamente
            publicacion.setFechaPublicacion(LocalDateTime.now());

            // Manejar la imagen subida (opcional)
            if (imagen != null && !imagen.isEmpty()) {
                String imagenUrl = guardarImagen(imagen); // Implementar método de guardado
                // Puedes guardar la URL de la imagen en la entidad si es necesario
                // publicacion.setImagenUrl(imagenUrl);
            }

            // Guardar la publicación en la base de datos
            publicacionService.save(publicacion);

        } catch (Exception e) {
            // Manejar excepciones
            e.printStackTrace();
            return "redirect:/error"; // Cambiar esta ruta según la página de error
        }

        // Redirigir a la página principal o lista de publicaciones
        return "redirect:/";
    }

    /**
     * Guarda la imagen en el sistema y retorna la URL.
     *
     * @param imagen Archivo de imagen.
     * @return URL o ruta del archivo guardado.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    private String guardarImagen(MultipartFile imagen) throws IOException {
        String rutaDirectorio = "uploads/"; // Ruta donde se guardarán las imágenes
        String nombreArchivo = System.currentTimeMillis() + "_" + imagen.getOriginalFilename(); // Nombre único
        java.nio.file.Path rutaArchivo = java.nio.file.Paths.get(rutaDirectorio, nombreArchivo);

        // Crear directorio si no existe
        java.nio.file.Files.createDirectories(rutaArchivo.getParent());

        // Guardar el archivo
        java.nio.file.Files.copy(imagen.getInputStream(), rutaArchivo);

        // Retornar la ruta del archivo guardado
        return rutaArchivo.toString();
    }
}

