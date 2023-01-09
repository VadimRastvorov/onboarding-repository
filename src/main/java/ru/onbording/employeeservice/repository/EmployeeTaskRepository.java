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
public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> { //todo почему Long если у тебя UUID?
    @Query(value = "SELECT * FROM public.tasks tasks WHERE task.id = :id",
            nativeQuery = true)
    EmployeeTask findTaskByTaskId(@Param("id") UUID id); //todo а зачем этот метод если есть findById

    @Query(value = "SELECT id, description, employee_id FROM public.tasks",
            nativeQuery = true)
    List<EmployeeTask> findAllTask(); //todo а зачем этот метод если есть findAll

    @Modifying
    @Query(value = "UPDATE tasks SET description = :description WHERE id = :id",
            nativeQuery = true)
        //todo зачем возвращать int? и мне даже интересно что за число возвращает))
    int updateTaskDescriptionById(@Param("id") UUID id, @Param("description") String description);

    @Query(value = "SELECT COUNT(*) FROM public.tasks WHERE employee_id = :employeeId",
            nativeQuery = true)
    int countTasksByEmployeeId(@Param("employeeId") long employeeId);

    @Modifying
    @Query(value = "DELETE FROM public.tasks tasks WHERE tasks.id = :id",
            nativeQuery = true)
    void deleteTaskById(@Param("id") UUID id); //todo а зачем этот метод если есть deleteById
}
