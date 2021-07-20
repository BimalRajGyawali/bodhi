package group.bonjai.bodhi.advices;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.responses.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConstraintViolationHandler {

    @ExceptionHandler
    public ResponseEntity<FailureResponse> handle(ConstraintViolation e){
        FailureResponse response = new FailureResponse(HttpStatus.BAD_REQUEST, e.getMessageMap());
        return new ResponseEntity<>(response.setMessage(e.getMessageMap()), HttpStatus.OK) ;
    }
}
