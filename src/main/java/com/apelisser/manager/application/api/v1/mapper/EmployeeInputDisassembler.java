package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.input.EmployeeInput;
import com.apelisser.manager.domain.model.Employee;
import com.apelisser.manager.domain.model.Position;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeInputDisassembler {

    private final ModelMapper mapper;

    public EmployeeInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Employee toDomainObject(EmployeeInput employeeInput) {
        return mapper.map(employeeInput, Employee.class);
    }

    public void copyToDomainObject(EmployeeInput employeeInput, Employee employee) {
        employee.setPosition(new Position());
        mapper.map(employeeInput, employee);
    }

}
