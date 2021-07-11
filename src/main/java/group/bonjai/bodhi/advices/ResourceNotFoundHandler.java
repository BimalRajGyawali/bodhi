package group.bonjai.bodhi.advices;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.responses.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundHandler {

    @ExceptionHandler
    public ResponseEntity<FailureResponse> handle(ResourceNotFound e){
        FailureResponse failureResponse = new FailureResponse(HttpStatus.NOT_FOUND, e.getMessageMap());
        return new ResponseEntity<>(failureResponse, failureResponse.getStatus());
    }
}
