package com.github.maximovj.libhubtec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) 
    {
        registry.addMapping("/**") // Habilitar CORS para todas las rutas
        .allowedOrigins("http://192.168.33.99:4200") // Especificar orígenes permitidos
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
        .allowedHeaders("*") // Permitir todos los headers
        .allowCredentials(true); // Si usas cookies o autenticación
    }

}
