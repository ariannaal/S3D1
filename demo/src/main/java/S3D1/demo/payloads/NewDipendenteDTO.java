package S3D1.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(



        @NotEmpty(message = "Lo username del dipendente e' obbligatorio")
        @Size(min = 3, max = 25, message = "Lo username del dipendente deve essere compresa tra 3 e 25 caratteri")
        String username,

        @NotEmpty(message = "Il nome del dipendente e' obbligatorio")
        @Size(min = 3, max = 25, message = "Il nome del dipendente deve essere compresa tra 3 e 25 caratteri")
        String nome,

        @NotEmpty(message = "Il cognome del dipendente e' obbligatorio")
        @Size(min = 3, max = 25, message = "Il cognome del dipendente deve essere compresa tra 3 e 25 caratteri")
        String cognome,

        @NotEmpty(message = "L'email del dipendente e' obbligatorio")
        @Size(min = 3, max = 25, message = "L'email del dipendente deve essere compresa tra 3 e 25 caratteri")
        String email


) {
}
