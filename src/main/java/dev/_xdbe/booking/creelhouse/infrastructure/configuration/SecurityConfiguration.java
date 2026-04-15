package dev._xdbe.booking.creelhouse.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/dashboard").hasRole("ADMIN")
                .anyRequest().permitAll()
            )
            .formLogin(withDefaults())
            
            .csrf((csrf) -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers ->
                headers.frameOptions(frameOptions ->
                    frameOptions.disable()
                )
            )
            .build();
    }

    // Step 3: add InMemoryUserDetailsManager
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails administrator = User.builder()
            .username("admin")
            .password("{bcrypt}$2b$10$d5BGL7urxl3402AQJJ5CYu6ORWQilg/InmcPNcoO0rjm3ZWf0NKGa")
            .roles("ADMIN")
            .build();

        UserDetails guest = User.builder()
            .username("guest")
            .password("{bcrypt}$2b$10$d5BGL7urxl3402AQJJ5CYu6ORWQilg/InmcPNcoO0rjm3ZWf0NKGa")
            .roles("GUEST")
            .build();

        return new InMemoryUserDetailsManager(administrator, guest);
    }
    // Step 3: end

}
