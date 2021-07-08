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
public class Teacher {
    public static final String TEACHER = "TEACHER";
    public static final String HOD = "HOD";

    @Id
    @GeneratedValue
    private UUID id;

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
