package group.bonjai.bodhi.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/*
JPA needs public default constructor.
Lombok Builder needs package level All args constructor
* **/

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor()
@Getter
@Setter()
public class DepartmentMember {
    public static final String TEACHER = "TEACHER";
    public static final String HOD = "HOD";
    public static final String ASSISTANT = "ASSISTANT";

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Department department;
}
