package by.itacademy.taskservice.core.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Messages {
    public static final String PROJECT_EXIST_RESPONSE = "Project with this name exists";
    public static final String NAME_UNIQUE_CONSTRAINT = "projects_name_unique";
    public static final String PROJECT_CREATED = "Project: \"%s\" was created";
    public static final String PROJECT_UPDATED = "Project: \"%s\" was updated";
    public static final String ERROR_GET_RESPONSE = "Failed to get project(s). Try again or contact support!";
    public static final String ERROR_GET_PAGE_BY_USER = "This user does not participate any project!";
    public static final String PROJECT_NOT_EXIST_RESPONSE = "Project with this id does not exist!";
    public static final String ERROR_UPDATE_RESPONSE_PROJECT = "Failed to update project. Wrong coordinates!";
    public static final String INCORRECT_DATA = "The request contains incorrect data. Change request and try again or contact support!";
    public static final String INCORRECT_CHARACTERS = "Incorrect characters. Change request and try it again!";
    public static final String SERVER_ERROR = "Internal server Error. Please, contact support!";
    public static final String REQUEST_ERROR = "Wrong dates. Problem in Project or(and) implementer. Try again or contact support!";
    public static final String TITLE_UNIQUE_CONSTRAINT = "tasks_title_unique";
    public static final String TASK_EXIST_RESPONSE = "Task with this title exists";
    public static final String TASK_CREATED = "Task: \"%s\" was created";
    public static final String TASK_UPDATED = "Task: \"%s\" was updated";
    public static final String TASK_NOT_EXIST_RESPONSE = "Task with this id does not exist!";
    public static final String TASK_FORBIDDEN_RESPONSE = "You do not have access to this task!";
    public static final String ERROR_UPDATE_RESPONSE_TASK = "Failed to update task. Wrong coordinates!";
}
