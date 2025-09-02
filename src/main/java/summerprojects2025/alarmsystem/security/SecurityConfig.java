package summerprojects2025.alarmsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /// Dependency injection JwtRequestFilter (constructor),
    /// defines public vs protected endpoints, public: permitAll and protected to be authenticated
    /// it disables csrf since using "Bearer " tokens, and a stateless session management.

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /// Generally, CSRF happens when a browser automatically adds headers (i.e: Session ID within a Cookie),
        /// and then made the session authenticated. Bearer tokens, or other HTTP header based tokens
        /// that need to be added manually, would prevent you from CSRF.
        return httpSecurity
                // disabling csrf, since using JWT bearer tokens
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //public endpoints
                        .requestMatchers("/api/auth/login", "/api/auth/register", "/api/auth/refresh-token", "/api/central-units/register", "/access/hello", "/api/central-units/all", "/api/central-units/unoccupied").permitAll()
                        //protected endpoints
                        .anyRequest().authenticated()
                )

                //stateless session management, jwt doesnt use sessions
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
