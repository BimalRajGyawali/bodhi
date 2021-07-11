package group.bonjai.bodhi.responses;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public abstract class HttpResponse implements Serializable {
    protected final LocalDateTime timestamp;
    protected final HttpStatus status;
    protected final boolean success;

    public HttpResponse(HttpStatus status, boolean success) {
       this.status = status;
        this.timestamp = LocalDateTime.now();
        this.success = success;
    }
    public HttpResponse(HttpStatus status){
        this(status, true);
    }
}
