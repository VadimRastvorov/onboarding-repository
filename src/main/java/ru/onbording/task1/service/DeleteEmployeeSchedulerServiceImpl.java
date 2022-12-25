package ru.onbording.task1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.task1.model.EmployeeDto;
import ru.onbording.task1.repository.EmployeeRepository;

@Service
public class DeleteEmployeeSchedulerServiceImpl implements DeleteEmployeeSchedulerService {
    private static final Logger log = LoggerFactory.getLogger(DeleteEmployeeSchedulerServiceImpl.class);

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
