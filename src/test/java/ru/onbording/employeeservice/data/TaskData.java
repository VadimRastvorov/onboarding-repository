package ru.onbording.employeeservice.data;

import ru.onbording.employeeservice.config.MessageBundleConfig;
import ru.onbording.employeeservice.dto.ResponseMessageDto;
import ru.onbording.employeeservice.dto.ResponseTaskMessagesDto;
import ru.onbording.employeeservice.dto.TaskDto;
import ru.onbording.employeeservice.entity.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TaskData {

    public static TaskDto createTaskDtoById() {
        return TaskDto.builder()
                .uuid("631e0556-8f3a-4e58-9039-4e61fcba217a")
                .employeeId("6")
                .description("task_1_employee_6")
                .build();
    }

    public static List<TaskDto> createTaskDtoListByEmployeeId() {
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(TaskDto.builder()
                .uuid("631e0556-8f3a-4e58-9039-4e61fcba217a")
                .employeeId("6")
                .description("task_1_employee_6")
                .build());
        taskDtoList.add(TaskDto.builder()
                .uuid("facc1b52-2b52-4191-b231-a7aa8c859703")
                .employeeId("6")
                .description("task_2_employee_6")
                .build());
        return taskDtoList;
    }

    public static List<TaskDto> createTaskDtoListByAll() {
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(TaskDto.builder()
                .uuid("d4189f5f-2ec9-4097-98c9-f6186c26b5da")
                .employeeId("1")
                .description("task_1_employee_1")
                .build());
        taskDtoList.add(TaskDto.builder()
                .uuid("31249236-7681-426f-880c-a6fac2adbc9e")
                .employeeId("2")
                .description("task_1_employee_2")
                .build());
        taskDtoList.add(TaskDto.builder()
                .uuid("9f272620-0e53-4453-a4a7-8654819b0933")
                .employeeId("3")
                .description("task_1_employee_3")
                .build());
        return taskDtoList;
    }

    public static TaskDto createTaskDto() {
        return TaskDto.builder()
                .uuid("a68b60a2-ece4-4db2-a644-f4cf8e75d6e9")
                .description("task_1_employee_5")
                .employeeId("5")
                .build();
    }

    public static Task createTask() {
        return Task.builder()
                .uuid(UUID.fromString("a68b60a2-ece4-4db2-a644-f4cf8e75d6e9"))
                .description("task_1_employee_5")
                .employeeId(5L)
                .build();
    }

    public static TaskDto createTaskDtoToUpdate(String uuid) {
        return TaskDto.builder()
                .uuid(uuid)
                .description("task_4_employee_4")
                .employeeId("4")
                .build();
    }

    public static TaskDto createTaskDtoToInsert(String uuid) {
        return TaskDto.builder()
                .uuid(uuid)
                .description("task_1_employee_4")
                .employeeId("4")
                .build();
    }

    public static ResponseTaskMessagesDto createResponseTaskMessagesDtoInsert(String uuid) {
        return ResponseTaskMessagesDto.builder()
                .taskDto(createTaskDtoToInsert(uuid))
                .messages(Collections.singletonList(ResponseMessageDto.builder()
                        .message("Задача '" + uuid + "' добавлена работнику '4'")
                        .build()))
                .build();
    }

    public static ResponseMessageDto createResponseMessageDtoUpdate(String uuid) {
        return ResponseMessageDto.builder()
                .message(MessageBundleConfig.getMessage("task.updateRow",
                        uuid))
                .build();
    }
    public static ResponseMessageDto createResponseMessageDtoDelete(String uuid) {
        return ResponseMessageDto.builder()
                .message(MessageBundleConfig.getMessage("task.deleteRow",
                        uuid))
                .build();
    }
}
