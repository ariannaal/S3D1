package S3D1.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
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

}
