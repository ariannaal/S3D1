package S3D1.demo.controllers;

import S3D1.demo.exceptions.BadRequestEx;
import S3D1.demo.payloads.DipendenteLoginDTO;
import S3D1.demo.payloads.DipendenteLoginRespDTO;
import S3D1.demo.payloads.NewDipendenteDTO;
import S3D1.demo.payloads.NewDipendenteRespDTO;
import S3D1.demo.services.AuthService;
import S3D1.demo.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendentiService;

    @PostMapping("/login")
    public DipendenteLoginRespDTO login(@RequestBody DipendenteLoginDTO payload) {
        return new DipendenteLoginRespDTO(this.authService.checkCredentialsAndGenerateToken(payload));
    }

    // 2. POST http://localhost:3001/users (+req.body)
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteRespDTO save(@RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            // Se ci sono stati errori lanciamo un'eccezione custom
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestEx("Ci sono stati errori nel payload. " + messages);
        } else {

            return new NewDipendenteRespDTO(this.dipendentiService.save(body).getId());
        }
    }

}
