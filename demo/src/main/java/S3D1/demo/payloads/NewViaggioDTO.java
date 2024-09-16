package S3D1.demo.payloads;

import S3D1.demo.enums.StatoViaggio;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewViaggioDTO(

        @NotEmpty(message = "La destinazione del viaggio e' obbligatoria")
        @Size(min = 3, max = 25, message = "La destinazione deve essere compresa tra 3 e 25 caratteri")
        String destinazione,

        @NotNull(message = "La data del viaggio e' obbligatoria")
        LocalDate dataViaggio,

        @NotNull(message = "Lo stato del viaggio e' obbligatorio")
        StatoViaggio statoViaggio


) {
}
