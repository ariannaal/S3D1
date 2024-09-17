package S3D1.demo.security;

import S3D1.demo.entities.Dipendente;
import S3D1.demo.exceptions.UnauthorizedException;
import S3D1.demo.services.DipendenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private DipendenteService dipendenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Per favore inserisci correttamente il token nell'Authorization Header");

        String accessToken = authHeader.substring(7);
        System.out.println("ACCESS TOKEN " + accessToken);

        jwtTools.verifyToken(accessToken);

        // Se voglio abilitare l'AUTORIZZAZIONE devo 'informare' Spring Security su chi sia l'utente che sta effettuando la richiesta, in modo
        // tale che possa controllarne il ruolo
        //cerco l'utente tramite id

        String id = jwtTools.getIdFromToken(accessToken);
        Dipendente dipendente = this.dipendenteService.findById(Integer.parseInt(id));
        //trovato l'utente posso associarlo al Security Context
        Authentication authentication = new UsernamePasswordAuthenticationToken(dipendente, null, dipendente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication); //associo l'utente autenticato (Autentication) al Context



        filterChain.doFilter(request, response);


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}
