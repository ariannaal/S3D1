package S3D1.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Config {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // devo disabilitare dei comportamenti di default
        httpSecurity.formLogin(http -> http.disable()); // no form di login
        httpSecurity.csrf(http -> http.disable());
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // non voglio le sessioni
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll()); // evita di ricevere 401 su OGNI richiesta
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder(11);//numero di rounds, ovvero quante volte viene eseguito l'algoritmo
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
