package ru.onbording.task1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onbording.task1.model.Employee;
import ru.onbording.task1.repository.EmployeeRepository;

import java.util.ArrayList;

@Service
public class DeleteEmployeeSchedulerImpl implements DeleteEmployeeScheduler { //todo если это сервис то название должно - SchedulerService
    private static final Logger log = LoggerFactory.getLogger(DeleteEmployeeSchedulerImpl.class); //todo Slf4j ?
    private EmployeeRepository employeeRepository; //todo final

    @Autowired
    public DeleteEmployeeSchedulerImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteEmployee() {
        Employee employee = employeeRepository.findAll().get(0); //todo если в бд нет сотрудников поймаешь IndexOutOfBoundsException
        employeeRepository.delete(employee);
        log.info("delete employee = {} {}, left {}", employee.getLastName(), employee.getFirstName(), employeeRepository.count());
    }
}
