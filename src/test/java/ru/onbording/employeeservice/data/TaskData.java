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

    public static Task createTaskToUpInsert(){
        return Task.builder()
                .uuid(UUID.randomUUID())
                .description("task_1_employee_4")
                .employeeId(4L)
                .build();
    }

    public static TaskDto createTaskDtoToUpdate(){
        return TaskDto.builder()
                .uuid("31249236-7681-426f-880c-a6fac2adbc9e")
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
