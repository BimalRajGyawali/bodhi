package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.models.DepartmentNotice;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ListAllDepartmentNoticesResponse extends HttpResponse{
    private List<Notice> notices;
    @Data
    @Builder
    final static class Notice{
            private final UUID id;
            private final String title;
            private final String content;
            private final String createdOn;
            private final NoticeDepartment department;
            private final Creator creator;
    }
    @Data
    @Builder
    final static class Creator{
        private final UUID id;
        private final String firstName;
        private final String middleName;
        private final String lastName;
        private final String email;
        private final String role;
        private final NoticeDepartment department;
    }
    @Data
    @Builder
    final static class NoticeDepartment{
        private final UUID id;
        private final String fullName;
        private final String shortName;
    }

    public ListAllDepartmentNoticesResponse(HttpStatus status, List<DepartmentNotice> departmentNotices) {
        super(status);
        //departmentNotices -> Notices
        this.notices = departmentNotices.stream()
                .map(departmentNotice -> {
                   return Notice.builder()
                           .id(departmentNotice.getId())
                           .title(departmentNotice.getTitle())
                           .content(departmentNotice.getContent())
                           .department(mapDepartmentToNoticeDepartment(departmentNotice.getDepartment()))
                           .creator(mapDepartmentMemberToCreator(departmentNotice.getCreator()))
                           .build();
                }).collect(Collectors.toList());
    }
    private NoticeDepartment mapDepartmentToNoticeDepartment(Department department){
        return NoticeDepartment.builder()
                .id(department.getId())
                .fullName(department.getFullName())
                .shortName(department.getShortName())
                .build();
    }
    public Creator mapDepartmentMemberToCreator(DepartmentMember departmentMember){
        return Creator.builder()
                .id(departmentMember.getId())
                .firstName(departmentMember.getFirstName())
                .middleName(departmentMember.getMiddleName())
                .lastName(departmentMember.getLastName())
                .email(departmentMember.getEmail())
                .role(departmentMember.getRole())
                .department(mapDepartmentToNoticeDepartment(departmentMember.getDepartment()))
                .build();
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public ListAllDepartmentNoticesResponse setNotices(List<Notice> notices) {
        this.notices = notices;
        return this;
    }
}
