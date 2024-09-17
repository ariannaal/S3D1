package S3D1.demo.services;

import S3D1.demo.entities.Dipendente;
import S3D1.demo.exceptions.UnauthorizedException;
import S3D1.demo.payloads.DipendenteLoginDTO;
import S3D1.demo.payloads.NewDipendenteDTO;
import S3D1.demo.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(DipendenteLoginDTO body) {

        Dipendente found = this.dipendenteService.findByEmail(body.email());
        System.out.println( bcrypt.encode(found.getUsername()));
        if (bcrypt.matches(body.username(), found.getUsername())) {

            return jwtTools.createToken(found);
        } else {

            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
