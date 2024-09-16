package S3D1.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DipendenteLoginDTO(

        @NotEmpty(message = "L'email è obbligatoria")
        String email,

        @NotEmpty(message = "Lo username è obbligatoria")
        @Size(min = 6, message = "Lo username deve essere lunga almeno 6 caratteri")
        String username


) {


}
