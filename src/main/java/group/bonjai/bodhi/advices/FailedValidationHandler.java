package group.bonjai.bodhi.advices;

import group.bonjai.bodhi.responses.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FailedValidationHandler {

    @ExceptionHandler
        public ResponseEntity<FailureResponse> handle(MethodArgumentNotValidException e){
        Map<String, String> failureMessageMap = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error ->{
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = ((FieldError) error).getDefaultMessage();
                    failureMessageMap.put(fieldName, errorMessage);
        });
        FailureResponse failureResponse = new FailureResponse(HttpStatus.OK, failureMessageMap);
        return new ResponseEntity<>(failureResponse, failureResponse.getStatus());
    }
}
