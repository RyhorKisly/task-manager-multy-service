package by.itacademy.commentsservice.dao.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String text;
    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private LocalDateTime dateTimeCreate;
    @Column(name = "task_id")
    private Long taskId;

    public Comment() {
    }

    public Comment(
            Long id,
            Long userId,
            String text,
            LocalDateTime dateTimeCreate,
            Long taskId
    ) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.dateTimeCreate = dateTimeCreate;
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(LocalDateTime dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
