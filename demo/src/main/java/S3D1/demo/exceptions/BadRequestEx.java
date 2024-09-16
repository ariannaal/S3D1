package S3D1.demo.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestEx extends RuntimeException {
    private List<ObjectError> errorsList;

    public BadRequestEx(String message) {
        super(message);
    }

    public BadRequestEx(List<ObjectError> errorsList) {
        super("Errori nel body");
        this.errorsList = errorsList;
    }

    public List<ObjectError> getErrorsList() {
        return errorsList;
    }

}
