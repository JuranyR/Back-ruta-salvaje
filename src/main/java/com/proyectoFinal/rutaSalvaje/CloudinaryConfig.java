package com.proyectoFinal.rutaSalvaje;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwkebeih9", // Copiado de tu pantalla
                "api_key", "336947142978298",
                "api_secret", "XdH8S4hXeB0EIMl3T8MPRPCCBww"
        ));
    }
}
