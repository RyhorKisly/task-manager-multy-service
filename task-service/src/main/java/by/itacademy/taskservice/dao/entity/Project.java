package by.itacademy.taskservice.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
public class Project {
    //TODO Почему мы пишем именно так а не просто @JeneratedValue
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String name;
    private String description;
    @ElementCollection(targetClass = Long.class)
    @CollectionTable(name = "users", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "book", nullable = false)
    private List<Long> users;

    public Project() {
    }

    public Project(
            Long id,
            String name,
            String description,
            List<Long> users
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }
}
