package group.bonjai.bodhi.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResourceNotFound extends Throwable{
    private final UUID resourceId;

    public ResourceNotFound(UUID resourceId) {
        this.resourceId = resourceId;
    }

    public String getMessage(){
        return "Id "+resourceId+" Not Found";
    }

    public Map<String, String> getMessageMap(){
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", getMessage());
        return messageMap;
    }
}
