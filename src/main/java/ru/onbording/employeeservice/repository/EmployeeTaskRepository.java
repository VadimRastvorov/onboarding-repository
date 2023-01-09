package ru.onbording.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.onbording.employeeservice.entity.EmployeeTask;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {
    @Query(value = "SELECT * FROM PUBLIC.TASKS TASKS WHERE TASK.ID = :id",
            nativeQuery = true)
    EmployeeTask findTaskByTaskId(@Param("id") UUID id);

    @Query(value = "SELECT ID, description, employee_id FROM PUBLIC.TASKS",
            nativeQuery = true)
    List<EmployeeTask> findAllTask();

    @Modifying
    @Query(value = "UPDATE TASKS SET DESCRIPTION = :description WHERE ID = :id",
            nativeQuery = true)
    int updateTaskDescriptionById(@Param("id") UUID id, @Param("description") String description);

    @Query(value = "SELECT COUNT(*) FROM PUBLIC.TASKS WHERE employee_id = :employeeId",
            nativeQuery = true)
    int countTasksByEmployeeId(@Param("employeeId") long employeeId);

    @Modifying
    @Query(value = "DELETE FROM PUBLIC.TASKS TASKS WHERE TASKS.ID = :id", //todo SQL запросы обычно пишут в UpperCase, чтобы было видно где команда, а где переменная //done
            nativeQuery = true)
    void deleteTaskById(@Param("id") UUID id);
}
