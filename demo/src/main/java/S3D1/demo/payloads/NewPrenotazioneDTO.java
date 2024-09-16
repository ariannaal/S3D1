package S3D1.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewPrenotazioneDTO(

        @NotNull(message = "La data della prenotazione e' obbligatoria")
        LocalDate dataPrenotazione,

        @NotEmpty(message = "Le note e/o preferenze sono obbligatorie")
        @Size(min = 3, max = 100, message = "Le note e/o preferenze devono essere comprese tra 3 e 100 caratteri")
        String noteEPreferenze,

        @NotNull(message = "L'ID del viaggio e' obbligatorio")
        Integer viaggioId,

        @NotNull(message = "L'ID del dipendente e' obbligatorio")
        Integer dipendenteId

) {
}
