package ru.onbording.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.onbording.employeeservice.entity.Task;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Modifying
    @Query(value = "UPDATE tasks SET description = :description WHERE id = :id",
            nativeQuery = true)
    void updateTaskDescriptionById(@Param("id") UUID id, @Param("description") String description);

    @Query(value = "SELECT COUNT(*) FROM public.tasks WHERE employee_id = :employeeId",
            nativeQuery = true)
    int countTasksByEmployeeId(@Param("employeeId") long employeeId);

    List<Task> findByEmployeeId(long employeeId);
}
