package amit.com.User_Management.config;

import amit.com.User_Management.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    //Password hashing (bcrypt)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Main Security Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // CSRF disable (JWT stateless hai)
                .csrf(csrf -> csrf.disable())

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // public APIs
                        .requestMatchers(
                                "/auth/signup",
                                "/auth/login"
                        ).permitAll()

                        // 🔥 ADMIN ONLY
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // baaki sab secure
                        .anyRequest().authenticated()
                )

                // 🔑 JWT filter BEFORE username-password filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
