package ru.onbording.employeeservice.data;

import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.entity.Task;

import java.util.UUID;

public class TaskData {
    public static Task createTaskToUpdate(){
        return Task.builder()
                .description("task_1_employee_4")
                .employeeId(4L)
                .build();
    }

    public static Task createTaskToInsert(){
        return Task.builder()
                .uuid(UUID.randomUUID())
                .description("task_1_employee_4")
                .employeeId(4L)
                .build();
    }

    public static TaskDto createTaskDtoToUpdate(){
        return TaskDto.builder()
                .uuid("b30cb4db-8595-43f8-99f3-c0544030300e")
                .description("task_1_employee_4")
                .employeeId("4")
                .build();
    }

    public static TaskDto createTaskDtoToInsert(){
        return TaskDto.builder()
                .description("task_1_employee_4")
                .employeeId("4")
                .build();
    }
}
