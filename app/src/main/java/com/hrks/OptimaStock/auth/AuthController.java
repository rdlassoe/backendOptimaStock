package com.hrks.OptimaStock.auth;

import com.hrks.OptimaStock.auth.dto.JwtResponse;
import com.hrks.OptimaStock.auth.dto.LoginRequest;
import com.hrks.OptimaStock.security.JwtUtil;
import com.hrks.OptimaStock.user.model.User;
import com.hrks.OptimaStock.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Endpoint para login - genera token JWT
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            // Si la autenticación es exitosa, generar token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);

            // Obtener información del usuario para enviar en la respuesta
            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            JwtResponse response = new JwtResponse(
                    jwt,
                    user.getUsername(),
                    user.getRole(),
                    jwtUtil.getExpirationTime());

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Credenciales inválidas");
            error.put("message", "Usuario o contraseña incorrectos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error en la autenticación");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * Endpoint opcional para registrar nuevos usuarios (solo para pruebas)
     * En producción, esto debería estar protegido y solo accesible por ADMIN
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Verificar si el usuario ya existe
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El username ya existe");
                return ResponseEntity.badRequest().body(error);
            }

            // Encriptar la contraseña antes de guardar
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Asegurar que el rol tenga el prefijo ROLE_
            if (user.getRole() != null && !user.getRole().startsWith("ROLE_")) {
                user.setRole("ROLE_" + user.getRole());
            }

            User savedUser = userRepository.save(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            response.put("username", savedUser.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al registrar usuario");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
