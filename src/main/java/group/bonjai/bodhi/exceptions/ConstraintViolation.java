package group.bonjai.bodhi.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ConstraintViolation extends Throwable{
    private final String fieldName;
    private final String errorMessage;

    public ConstraintViolation(String fieldName, String errorMessage){
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getMessageMap(){
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(fieldName, errorMessage);
        return messageMap;
    }
}
