package lf.todolist.persistence.jpa.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "LF_TODO_ITEM")
public class TodoItemEntity extends AuditableBaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    UUID uuid;

    @Column(name = "todo_uuid")
    UUID todoUuid;

    @Column(name = "item_name")
    String itemName;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = TodoEntity.class)
    @JoinColumn(name = "todo_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
    TodoEntity todo;
}
