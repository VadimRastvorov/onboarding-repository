package ru.onbording.employeeservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.onbording.employeeservice.DatabaseTest;
import ru.onbording.employeeservice.entity.Task;
import ru.onbording.employeeservice.exception.ResourceNotFoundException;


import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTest extends DatabaseTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testFetchTasksALl() {
        List<Task> tasks = taskRepository.findAll();
        assertNotNull(tasks);
        assertEquals(10, tasks.size());
    }

    @Test
    void testFetchTasksByIdWithValidId() {
        Task task = taskRepository.findById(UUID.fromString("d4189f5f-2ec9-4097-98c9-f6186c26b5da"))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Не найдена запись с id 'd4189f5f-2ec9-4097-98c9-f6186c26b5da'"));
        assertEquals("task_1_employee_1", task.getDescription());
    }

    @Test
    void testFetchTasksByIdWithOuValidId() {
        assertThrows(
                ResourceNotFoundException.class,
                () -> {
                    taskRepository.findById(UUID.fromString("8b2e75a2-988a-43fa-84e1-1575de2a0840"))
                            .orElseThrow(() -> new ResourceNotFoundException("8b2e75a2-988a-43fa-84e1-1575de2a0840"));
                });
    }

    @Test
    void testFetchTasksByEmployeeId() {
        List<Task> tasks = taskRepository.findByEmployeeId(1L);
        assertNotNull(tasks);
        assertEquals(5, tasks.size());
    }

    @Test
    void testInsertAndDeleteTask() {
        Task task = taskRepository.save(Task.builder()
                .description("task_1_employee_4")
                .employeeId(4L)
                .build());
        UUID uuid = task.getUuid();
        task = taskRepository.findById(task.getUuid())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Не найдена запись с id '" + uuid.toString() + "'"));
        assertEquals(4L, task.getEmployeeId());
        assertEquals("task_1_employee_4", task.getDescription());

        assertEquals(11, taskRepository.findAll().size());

        taskRepository.deleteById(uuid);
        assertThrows(
                ResourceNotFoundException.class,
                () -> {
                    taskRepository.findById(uuid)
                            .orElseThrow(() -> new ResourceNotFoundException("Не найдена запись с id '" + uuid.toString() + "'"));
                });
        assertEquals(10, taskRepository.findAll().size());
    }

    @Test
    void testCountTasksByEmployeeId() {
        assertEquals(5, taskRepository.countTasksByEmployeeId(2L));
    }

    @Test
    void testUpdateTasks() {
        taskRepository.save(Task.builder()
                .uuid(UUID.fromString("31249236-7681-426f-880c-a6fac2adbc9e"))
                .description("task_1_employee_3")
                .build());
        Task task = taskRepository.findById(UUID.fromString("31249236-7681-426f-880c-a6fac2adbc9e"))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Не найдена запись с id '31249236-7681-426f-880c-a6fac2adbc9e'"));
        assertEquals("task_1_employee_3", task.getDescription());
    }
}
