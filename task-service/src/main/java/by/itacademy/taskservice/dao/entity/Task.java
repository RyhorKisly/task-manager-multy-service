package by.itacademy.taskservice.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String header;
    private String description;
    private Long executor;
    @Column(name = "project_id")
    private Long projectId;
    @ManyToOne
    private TaskStatus status;
    @ManyToOne
    private Priority priority;
    @ManyToOne
    private Category category;
    //TODO Для реализации файлового хранилища, храните адрес сервера (если их несколько) и полный путь к файлу
    // на сервере в таблице и отдавайте файлы через nginx.
    @OneToMany
    private List<File> files;
    @Column(name = "dead_line")
    private LocalDateTime deadline;
    @CreationTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create")
    private LocalDateTime dateTimeCreate;
    @UpdateTimestamp(source = SourceType.DB)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_update")
    private LocalDateTime dateTimeUpdate;
    @Column(name = "dt_close")
    private LocalDateTime dateTimeClose;


    public Task() {
    }

    public Task(
            Long id,
            String header,
            String description,
            Long executor,
            Long projectId,
            TaskStatus status,
            Priority priority,
            Category category,
            List<File> files,
            LocalDateTime deadline,
            LocalDateTime dateTimeCreate,
            LocalDateTime dateTimeUpdate,
            LocalDateTime dateTimeClose
    ) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.executor = executor;
        this.projectId = projectId;
        this.status = status;
        this.priority = priority;
        this.category = category;
        this.files = files;
        this.deadline = deadline;
        this.dateTimeCreate = dateTimeCreate;
        this.dateTimeUpdate = dateTimeUpdate;
        this.dateTimeClose = dateTimeClose;
    }

    public Long getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getExecutor() {
        return executor;
    }

    public void setExecutor(Long executor) {
        this.executor = executor;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(LocalDateTime dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public LocalDateTime getDateTimeUpdate() {
        return dateTimeUpdate;
    }

    public void setDateTimeUpdate(LocalDateTime dateTimeUpdate) {
        this.dateTimeUpdate = dateTimeUpdate;
    }

    public LocalDateTime getDateTimeClose() {
        return dateTimeClose;
    }

    public void setDateTimeClose(LocalDateTime dateTimeClose) {
        this.dateTimeClose = dateTimeClose;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", executor=" + executor +
                ", projectId=" + projectId +
                ", status=" + status +
                ", priority=" + priority +
                ", category=" + category +
                ", files=" + files +
                ", deadline=" + deadline +
                ", dateTimeCreate=" + dateTimeCreate +
                ", dateTimeUpdate=" + dateTimeUpdate +
                ", dateTimeClose=" + dateTimeClose +
                '}';
    }
}