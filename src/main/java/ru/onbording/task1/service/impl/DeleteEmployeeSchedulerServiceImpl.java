//todo если это сервис то название должно - SchedulerService //done
package ru.onbording.task1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.task1.model.EmployeeDto;
import ru.onbording.task1.repository.EmployeeRepository;
import ru.onbording.task1.service.DeleteEmployeeSchedulerService;

@Slf4j
@Service
public class DeleteEmployeeSchedulerServiceImpl implements DeleteEmployeeSchedulerService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DeleteEmployeeSchedulerServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteEmployee() {
        if (employeeRepository.count() > 0) {
            EmployeeDto employee = employeeRepository.findAll().get(0);
            //employeeRepository.delete(employee);
            log.info("delete employee = {} {}, left {}", employee.getLastName(), employee.getFirstName(), employeeRepository.count());
        }
    }
}
