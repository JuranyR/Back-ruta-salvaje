package com.proyectoFinal.rutaSalvaje.auth;

import com.proyectoFinal.rutaSalvaje.model.Rol;
import com.proyectoFinal.rutaSalvaje.model.Usuario;
import com.proyectoFinal.rutaSalvaje.repository.UsuarioRepository;
import com.proyectoFinal.rutaSalvaje.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "El email ya está registrado"));
        }

        Usuario usuario = new Usuario(
                request.getNombre(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getTelefono(),
                request.getNombreEmergencia(),
                request.getTelefonoEmergencia(),
                request.getParentesco(),
                Rol.USUARIO  // siempre USUARIO al registrarse
        );

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(Map.of(
                "mensaje", "Usuario registrado exitosamente",
                "email", usuario.getEmail(),
                "rol", usuario.getRol()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtUtil.generateToken(usuario);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", usuario.getEmail(),
                "rol", usuario.getRol(),
                "nombre", usuario.getNombre()
        ));
    }

    // Endpoint para que un ADMIN convierta a otro usuario en ADMIN
    @PutMapping("/promover/{email}")
    public ResponseEntity<?> promoverAdmin(@PathVariable String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Usuario no encontrado"));
        }
        usuario.setRol(Rol.ADMIN);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(Map.of(
                "mensaje", "Usuario promovido a ADMIN",
                "email", usuario.getEmail(),
                "rol", usuario.getRol()
        ));
    }
}