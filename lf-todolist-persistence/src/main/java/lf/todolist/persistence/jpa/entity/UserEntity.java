package lf.todolist.persistence.jpa.entity;

import javax.persistence.*;
import lf.todolist.intf.enums.GenderEnum;
import lf.todolist.intf.enums.RoleEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "LF_USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity extends AuditableBaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    UUID uuid;

    String username;

    String password;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "national_id")
    String nationalId;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @Column(name = "country_code")
    String countryCode;

    String mobile;

    String email;

    @Column(name = "address_1")
    String address1;

    @Column(name = "address_2")
    String address2;

    @Column(name = "address_3")
    String address3;

    Boolean locked;

    @Column(name = "last_login")
    LocalDateTime lastLogin;

    @Column(name = "number_of_retries")
    Integer numberOfRetries;

    @Enumerated(EnumType.STRING)
    RoleEnum role;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    List<TodoEntity> todoList;
}
