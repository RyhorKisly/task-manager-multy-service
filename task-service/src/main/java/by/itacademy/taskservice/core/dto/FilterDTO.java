package by.itacademy.taskservice.core.dto;

import by.itacademy.taskservice.core.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

public class FilterDTO {
    private List<UUID> projects;
    private List<UUID> implementers;
   private List<TaskStatus> statuses;

    public FilterDTO() {
    }

    public FilterDTO(List<UUID> projects, List<UUID> implementers, List<TaskStatus> statuses) {
        this.projects = projects;
        this.implementers = implementers;
        this.statuses = statuses;
    }

    public List<UUID> getProjects() {
        return projects;
    }

    public void setProjects(List<UUID> projects) {
        this.projects = projects;
    }

    public List<UUID> getImplementers() {
        return implementers;
    }

    public void setImplementers(List<UUID> implementers) {
        this.implementers = implementers;
    }

    public List<TaskStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<TaskStatus> statuses) {
        this.statuses = statuses;
    }
}
