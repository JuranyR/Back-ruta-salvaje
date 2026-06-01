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
                plan("Plan Ruta del vertigo",
                        "Ponle el pecho al vacío en la experiencia más extrema de Ruta Salvaje. Cruza un puente tibetano suspendido a más de 80 metros de altura, lánzate al vacío con bungee jumping, deslízate a alta velocidad en canopy, supera la pared de escalada en roca y termina con el columpio extremo que te dejará sin palabras. Una jornada diseñada para quienes necesitan superar sus propios límites.",
                        310000.0, 0.0, TipoPlan.EXTREMO, Dificultad.ALTA,
                        "Puente tibetano, Bungee Jumping, Canopy, Escalada en roca, Columpio extremo",
                        "./imagenes/planes/Plan-RutaDelVertigo.png"),

                plan("Plan Desafio Total",
                        "Una jornada de adrenalina pura de principio a fin. Empieza dominando las aguas bravas del río en una sesión de canotaje donde cada rápido pone a prueba tu trabajo en equipo, y culmina lanzándote al vacío con bungee jumping desde una altura impresionante. No hay descanso, no hay pausa, solo acción. El plan ideal para quienes lo quieren todo en un solo día.",
                        380000.0, 8.0, TipoPlan.EXTREMO, Dificultad.ALTA,
                        "Canotaje, Bungee Jumping",
                        "./imagenes/planes/Plan-DesafioTotal.png"),

                plan("Plan Explorador de las nubes",
                        "Elévate por encima del mundo en una experiencia que combina libertad y emoción. Vuela en parapente sobre paisajes de montaña que cortan la respiración, con vistas panorámicas de valles, ríos y cumbres que solo se aprecian desde las alturas. Luego deslízate entre los árboles en un circuito de canopy rodeado de naturaleza. Un plan para quienes sueñan con tocar el cielo.",
                        420000.0, 0.0, TipoPlan.EXTREMO, Dificultad.MEDIA,
                        "Parapente, Canopy",
                        "./imagenes/planes/Plan-ExploradordelasNubes.png"),

                plan("Plan Mision secreta: Operacion Selva",
                        "Tu misión, si decides aceptarla, es internarte en la selva y resolver una serie de enigmas, retos físicos y pruebas mentales en equipo antes de que el tiempo se acabe. Esta experiencia de aventura inmersiva combina la emoción de una búsqueda de pistas con desafíos de cooperación que pondrán a prueba la comunicación y la estrategia de tu grupo. Perfecta para familias y equipos que quieren vivir algo diferente.",
                        310000.0, 0.0, TipoPlan.FAMILIAR, Dificultad.MEDIA,
                        "Busqueda de pistas, Retos en equipo, Pruebas fisicas, Pruebas mentales",
                        "./imagenes/planes/Plan-MisionSecretaOperacionSelva.png"),

                plan("Plan Agua y roca",
                        "La naturaleza en su estado más salvaje te espera en este plan que combina el poder del agua y la roca. Navega los rápidos del río en canotaje, desciende cascadas verticales con torrentismo, nada en pozos naturales de agua cristalina y explora cascadas escondidas que pocos conocen. Una jornada húmeda, intensa y completamente memorable para los amantes del agua en movimiento.",
                        290000.0, 5.0, TipoPlan.EXTREMO, Dificultad.MEDIA,
                        "Canotaje, Torrentismo, Nado en rio, Exploracion de cascadas",
                        "./imagenes/planes/Plan-AguayRoca.png"),

                plan("Plan Travesia del horizonte",
                        "La aventura más completa de nuestro catálogo. Inicia con una jornada de senderismo por senderos de alta montaña, monta tu campamento bajo las estrellas en medio de la naturaleza, avista fauna silvestre en su hábitat natural y cierra la experiencia lanzándote en parapente sobre el horizonte al amanecer. Este plan está diseñado para quienes buscan una desconexión total y una reconexión profunda con la naturaleza.",
                        460000.0, 0.0, TipoPlan.EXTREMO, Dificultad.ALTA,
                        "Senderismo, Camping, Parapente, Avistamiento de fauna",
                        "./imagenes/planes/Plan-TravesiaDelHorizonte.png"),

                plan("Plan Caminos colgantes",
                        "La altura sin el vértigo extremo. Este plan familiar te lleva por senderos elevados entre los árboles, cruzando puentes colgantes seguros con vistas impresionantes del bosque. El circuito de equilibrio pone a prueba la coordinación y la concentración de toda la familia en un entorno seguro y controlado. Ideal para niños y adultos que quieren sentir la emoción de las alturas sin renunciar a la tranquilidad.",
                        160000.0, 0.0, TipoPlan.FAMILIAR, Dificultad.BAJA,
                        "Senderos en altura, Puentes colgantes, Circuito de equilibrio",
                        "./imagenes/planes/Plan-CaminosColgantes.png"),

                plan("Plan Supervivencia extrema",
                        "¿Qué harías si te quedas solo en la naturaleza? En este plan aprenderás las técnicas esenciales de supervivencia: cómo orientarte sin GPS, construir un refugio con materiales naturales, encender fuego y moverte por terreno desconocido. Una jornada de senderismo en condiciones reales cierra la experiencia. No es solo aventura, es conocimiento que puede salvarte la vida.",
                        340000.0, 12.0, TipoPlan.EXTREMO, Dificultad.ALTA,
                        "Supervivencia, Construccion de refugios, Senderismo, Orientacion",
                        "./imagenes/planes/Plan-Desafios.png"),

                plan("Plan Aventura en el bosque",
                        "El bosque como aula, la aventura como método. Este plan familiar invita a explorar la riqueza de la flora local a través de caminatas ecológicas guiadas, descubriendo especies de plantas, insectos y aves en su entorno natural. Los juegos de aventura al aire libre hacen que los más jóvenes se desconecten de las pantallas y se conecten de verdad con el mundo natural. Una experiencia educativa y divertida para toda la familia.",
                        195000.0, 0.0, TipoPlan.FAMILIAR, Dificultad.BAJA,
                        "Caminatas ecologicas, Exploracion de flora, Juegos de aventura",
                        "./imagenes/planes/Plan-AventuraEnElBosque.png"),

                plan("Plan El salto del caminante",
                        "Una salida que lo tiene todo: exigencia física, paisajes impresionantes y dosis generosas de adrenalina. Comienza con un trekking de ascenso hasta miradores con vistas privilegiadas, continúa con un descenso en rappel por paredes verticales de roca y cierra la jornada con un salto de bungee jumping que pone el broche perfecto a un día que no olvidarás. Para espíritus inquietos que no se conforman con uno solo.",
                        245000.0, 0.0, TipoPlan.EXTREMO, Dificultad.MEDIA,
                        "Trekking, Ascenso a miradores, Bungee Jumping, Rappel",
                        "./imagenes/planes/Plan-SaltoDelCaminante.png")
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
