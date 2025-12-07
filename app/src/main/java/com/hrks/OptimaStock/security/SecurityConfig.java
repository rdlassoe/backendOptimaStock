//package com.hrks.OptimaStock.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    /**
//     * Configuración principal de seguridad
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Deshabilitar CSRF ya que usamos JWT (stateless)
//                .csrf(csrf -> csrf.disable())
//
//                // Configurar autorización de requests
//                .authorizeHttpRequests(auth -> auth
//                        // Endpoints públicos (autenticación)
//                        .requestMatchers("/auth/**").permitAll()
//
//                        // Endpoints protegidos por rol
//                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/product/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/product/**").hasAnyRole("ADMIN", "EMPLEADO")
//
//                        .requestMatchers("/inventory/**").hasAnyRole("ADMIN", "EMPLEADO")
//                        .requestMatchers("/api/inventory-movements/**").hasRole("ADMIN")
//                        .requestMatchers("/sale/**").hasAnyRole("ADMIN", "EMPLEADO")
//                        .requestMatchers("/user/**").hasRole("ADMIN")
//
//                        // Resto de endpoints requieren autenticación
//                        .anyRequest().authenticated())
//
//                // Política de sesión stateless (no mantener sesiones en el servidor)
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                // Agregar el filtro JWT antes del filtro de autenticación estándar
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    /**
//     * Bean para encriptar contraseñas con BCrypt
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * Bean del AuthenticationManager para autenticar usuarios
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//}

package com.hrks.OptimaStock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Habilitar CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Deshabilitar CSRF ya que usamos JWT (stateless)
                .csrf(csrf -> csrf.disable())

                // Configurar autorización de requests
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos (autenticación)
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/category/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/category/**").permitAll()

                        // Endpoints protegidos por rol
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/**").hasAnyRole("ADMIN", "EMPLEADO")

                        .requestMatchers("/inventory/**").hasAnyRole("ADMIN", "EMPLEADO")
                        .requestMatchers("/api/inventory-movements/**").hasRole("ADMIN")
                        .requestMatchers("/sale/**").hasAnyRole("ADMIN", "EMPLEADO")
                        .requestMatchers("/user/**").hasRole("ADMIN")

                        // Resto requieren autenticación
                        .anyRequest().authenticated())

                // Política de sesión stateless (no mantener sesiones)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Agregar filtro JWT antes del de autenticación
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configuración Global de CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://localhost:4200"   // Angular
                // agrega más dominios si es necesario
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

