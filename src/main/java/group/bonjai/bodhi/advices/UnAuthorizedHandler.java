package group.bonjai.bodhi.advices;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.exceptions.UnAuthorized;
import group.bonjai.bodhi.responses.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class UnAuthorizedHandler {
    @ExceptionHandler
    public ResponseEntity<FailureResponse> handle(UnAuthorized e){
        FailureResponse failureResponse = new FailureResponse(HttpStatus.UNAUTHORIZED,e.getMessageMap());
        return new ResponseEntity<>(failureResponse, HttpStatus.OK);
    }
}
