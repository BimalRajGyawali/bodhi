package group.bonjai.bodhi.advices;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.responses.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BadCredentialsHandler {

    @ExceptionHandler
    public ResponseEntity<FailureResponse> handle(BadCredentialsException e){
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", "Invalid credentials");
        FailureResponse response = new FailureResponse(HttpStatus.BAD_REQUEST, messageMap);
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }
}
