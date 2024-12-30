package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.EmployeeResumeModel;
import com.apelisser.manager.domain.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeResumeModelAssembler {

    private final ModelMapper mapper;

    public EmployeeResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EmployeeResumeModel toModel(Employee employee) {
        return mapper.map(employee, EmployeeResumeModel.class);
    }

    public List<EmployeeResumeModel> toCollectionModel(List<Employee> employees) {
        return employees.stream()
            .map(this::toModel)
            .toList();
    }

}
