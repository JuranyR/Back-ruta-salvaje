package com.proyectoFinal.rutaSalvaje.config;

import com.proyectoFinal.rutaSalvaje.model.Dificultad;
import com.proyectoFinal.rutaSalvaje.model.Plan;
import com.proyectoFinal.rutaSalvaje.model.Rol;
import com.proyectoFinal.rutaSalvaje.model.TipoPlan;
import com.proyectoFinal.rutaSalvaje.model.Usuario;
import com.proyectoFinal.rutaSalvaje.repository.PlanRepository;
import com.proyectoFinal.rutaSalvaje.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PlanRepository planRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PlanRepository planRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.planRepository = planRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        crearAdminInicial();

        if (planRepository.count() > 0) {
            return;
        }

        planRepository.saveAll(List.of(
                plan("Plan Ruta del vertigo", "Puente tibetano, Bungee Jumping, Canopy, Escalada en roca y columpio extremo.", 310000.0, 0.0, TipoPlan.EXTREMO, Dificultad.ALTA, "Puente tibetano, Bungee Jumping, Canopy, Escalada en roca, Columpio extremo", "./imagenes/planes/Plan-RutaDelVertigo.png"),
                plan("Plan Desafio Total", "Canotaje y Bungee Jumping en una jornada de adrenalina completa.", 380000.0, 8.0, TipoPlan.EXTREMO, Dificultad.ALTA, "Canotaje, Bungee Jumping", "./imagenes/planes/Plan-DesafioTotal.png"),
                plan("Plan Explorador de las nubes", "Parapente y canopy con vistas panoramicas entre montanas.", 420000.0, 0.0, TipoPlan.EXTREMO, Dificultad.MEDIA, "Parapente, Canopy", "./imagenes/planes/Plan-ExploradordelasNubes.png"),
                plan("Plan Mision secreta: Operacion Selva", "Experiencia inmersiva con pistas, retos fisicos y retos mentales.", 310000.0, 0.0, TipoPlan.FAMILIAR, Dificultad.MEDIA, "Busqueda de pistas, Retos en equipo, Pruebas fisicas, Pruebas mentales", "./imagenes/planes/Plan-MisionSecretaOperacionSelva.png"),
                plan("Plan Agua y roca", "Canotaje, torrentismo y nado en rio para conectar con cascadas naturales.", 290000.0, 5.0, TipoPlan.EXTREMO, Dificultad.MEDIA, "Canotaje, Torrentismo, Nado en rio, Exploracion de cascadas", "./imagenes/planes/Plan-AguayRoca.png"),
                plan("Plan Travesia del horizonte", "Senderismo, camping y parapente para una experiencia completa.", 460000.0, 0.0, TipoPlan.EXTREMO, Dificultad.ALTA, "Senderismo, Camping, Parapente, Avistamiento de fauna", "./imagenes/planes/Plan-TravesiaDelHorizonte.png"),
                plan("Plan Caminos colgantes", "Senderos elevados y puentes colgantes seguros para familias.", 160000.0, 0.0, TipoPlan.FAMILIAR, Dificultad.BAJA, "Senderos en altura, Puentes colgantes, Circuito de equilibrio", "./imagenes/planes/Plan-CaminosColgantes.png"),
                plan("Plan Supervivencia extrema", "Tecnicas basicas de supervivencia, orientacion y refugios naturales.", 340000.0, 12.0, TipoPlan.EXTREMO, Dificultad.ALTA, "Supervivencia, Construccion de refugios, Senderismo, Orientacion", "./imagenes/planes/Plan-Desafios.png"),
                plan("Plan Aventura en el bosque", "Caminatas ecologicas y juegos de aventura en medio del bosque.", 195000.0, 0.0, TipoPlan.FAMILIAR, Dificultad.BAJA, "Caminatas ecologicas, Exploracion de flora, Juegos de aventura", "./imagenes/planes/Plan-AventuraEnElBosque.png"),
                plan("Plan El salto del caminante", "Trekking, rappel y bungee jumping en una salida llena de adrenalina.", 245000.0, 0.0, TipoPlan.EXTREMO, Dificultad.MEDIA, "Trekking, Ascenso a miradores, Bungee Jumping, Rappel", "./imagenes/planes/Plan-SaltoDelCaminante.png")
        ));
    }

    private void crearAdminInicial() {
        String email = "admin@rutasalvaje.com";
        if (usuarioRepository.findByEmail(email).isPresent()) {
            return;
        }

        Usuario admin = new Usuario(
                "Administrador Ruta Salvaje",
                email,
                passwordEncoder.encode("Admin123*"),
                "3000000000",
                "Contacto emergencia",
                "3000000001",
                "Administracion",
                Rol.ADMIN
        );

        usuarioRepository.save(admin);
    }

    private Plan plan(String nombre, String descripcion, Double precio, Double descuento, TipoPlan tipoPlan,
                      Dificultad dificultad, String actividades, String imagen) {
        Plan plan = new Plan();
        plan.setNombre(nombre);
        plan.setDescripcion(descripcion);
        plan.setPrecio(precio);
        plan.setDescuentoPorcentaje(descuento);
        plan.setTipoPlan(tipoPlan);
        plan.setDificultad(dificultad);
        plan.setEstado(true);
        plan.setActividades(actividades);
        plan.setImagen(imagen);
        return plan;
    }
}
