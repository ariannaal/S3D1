package S3D1.demo.exceptions;

import S3D1.demo.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


public class ExceptionsHandler {


    @ExceptionHandler(NotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundEx e) {
        return new ErrorsPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsPayload("Errore generico, risolveremo il prima possibile", LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsResponseDTO handleUnauthorized(UnauthorizedException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    // creo nuova gestione dell' errore per negata autorizzazione
    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorsResponseDTO handleForbidden(AuthorizationDeniedException ex){
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

}

