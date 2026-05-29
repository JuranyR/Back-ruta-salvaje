package com.proyectoFinal.rutaSalvaje.config;

import com.proyectoFinal.rutaSalvaje.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtFilter jwtFilter,
                          AuthenticationProvider authenticationProvider) {
        this.jwtFilter = jwtFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // ── Swagger: público ──────────────────────────────────────
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // ── Auth: registro y login son públicos ───────────────────
                        .requestMatchers("/auth/register", "/auth/login").permitAll()

                        // ── Promover a ADMIN: solo ADMIN ──────────────────────────
                        .requestMatchers(HttpMethod.PUT, "/auth/promover/**").hasRole("ADMIN")

                        // ── PLANES ────────────────────────────────────────────────
                        // Cualquiera autenticado puede ver planes
                        .requestMatchers(HttpMethod.GET, "/planes/**").hasAnyRole("ADMIN", "USUARIO")
                        // Solo ADMIN crea, edita y elimina planes
                        .requestMatchers(HttpMethod.POST, "/planes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/planes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/planes/**").hasRole("ADMIN")

                        // ── RESERVAS ──────────────────────────────────────────────
                        // ADMIN y USUARIO pueden ver, crear y cancelar reservas
                        .requestMatchers(HttpMethod.GET, "/reservas/**").hasAnyRole("ADMIN", "USUARIO")
                        .requestMatchers(HttpMethod.POST, "/reservas/**").hasAnyRole("ADMIN", "USUARIO")
                        .requestMatchers(HttpMethod.PATCH, "/reservas/**").hasAnyRole("ADMIN", "USUARIO")
                        // Solo ADMIN puede editar una reserva completa
                        .requestMatchers(HttpMethod.PUT, "/reservas/**").hasRole("ADMIN")

                        // ── RESEÑAS ───────────────────────────────────────────────
                        // ADMIN y USUARIO pueden ver y crear reseñas
                        .requestMatchers(HttpMethod.GET, "/resenas/**").hasAnyRole("ADMIN", "USUARIO")
                        .requestMatchers(HttpMethod.POST, "/resenas/**").hasAnyRole("ADMIN", "USUARIO")
                        // Solo ADMIN puede eliminar reseñas
                        .requestMatchers(HttpMethod.DELETE, "/resenas/**").hasRole("ADMIN")

                        // ── CONTACTOS ─────────────────────────────────────────────
                        // Cualquiera puede enviar un mensaje de contacto (sin login)
                        .requestMatchers(HttpMethod.POST, "/contactos/**").permitAll()
                        // Solo ADMIN puede ver y eliminar mensajes de contacto
                        .requestMatchers(HttpMethod.GET, "/contactos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/contactos/**").hasRole("ADMIN")

                        // ── USUARIOS ──────────────────────────────────────────────
                        // Solo ADMIN puede listar, crear, eliminar y ver usuarios
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")
                        // ADMIN y el propio USUARIO pueden editar su perfil
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("ADMIN", "USUARIO")

                        // ── Todo lo demás requiere estar autenticado ──────────────
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}