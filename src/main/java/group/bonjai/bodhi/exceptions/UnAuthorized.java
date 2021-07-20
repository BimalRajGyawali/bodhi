package group.bonjai.bodhi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class UnAuthorized extends Exception{
    private final String message;

    public UnAuthorized(){
        this.message = "UnAuthorized";
    }
    public Map<String, String> getMessageMap(){
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("role", "Unauthorized");
        return messageMap;
    }
}
