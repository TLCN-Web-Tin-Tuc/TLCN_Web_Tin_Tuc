package hcmute.edu.vn.modservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Error404 extends RuntimeException {
    public Error404(String message){
        super(message);
    }
}
