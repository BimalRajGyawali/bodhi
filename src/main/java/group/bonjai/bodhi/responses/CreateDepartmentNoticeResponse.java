package group.bonjai.bodhi.responses;


import group.bonjai.bodhi.models.DepartmentNotice;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;


public class CreateDepartmentNoticeResponse extends HttpResponse{
    private final Notice notice;

    @Data
    final static class Notice{
        private final UUID id;
        private final String title;
        private final String content;
        private final String createdOn;
    }

    public CreateDepartmentNoticeResponse(HttpStatus status, DepartmentNotice notice){
        super(status);
        this.notice = new Notice(notice.getId(), notice.getTitle(),
                notice.getContent(), notice.getCreatedOn().toString());
    }

    public Notice getNotice() {
        return notice;
    }
}
