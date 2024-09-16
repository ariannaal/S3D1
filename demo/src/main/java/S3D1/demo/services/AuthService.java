package S3D1.demo.services;

import S3D1.demo.entities.Dipendente;
import S3D1.demo.exceptions.UnauthorizedException;
import S3D1.demo.payloads.DipendenteLoginDTO;
import S3D1.demo.payloads.NewDipendenteDTO;
import S3D1.demo.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(DipendenteLoginDTO body) {
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite email se esiste il dipendente
        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if (found.getUsername().equals(body.username())) {
            // 1.2 Se lo trovo verifico se l'user trovata combacia con quella passataci tramite body
            // 2. Se è tutto ok --> genero un access token e lo restituisco
            return jwtTools.createToken(found);
        } else {
            // 3. Se le credenziali sono errate --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
