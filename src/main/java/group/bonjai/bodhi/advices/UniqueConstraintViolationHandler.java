package group.bonjai.bodhi.advices;

import group.bonjai.bodhi.exceptions.UniqueConstraintViolation;
import group.bonjai.bodhi.responses.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UniqueConstraintViolationHandler {

    @ExceptionHandler
    public ResponseEntity<FailureResponse> handle(UniqueConstraintViolation e){
        FailureResponse response = new FailureResponse(HttpStatus.BAD_REQUEST, e.getMessageMap());
        return new ResponseEntity<>(response.setMessage(e.getMessageMap()), response.getStatus()) ;
    }
}
