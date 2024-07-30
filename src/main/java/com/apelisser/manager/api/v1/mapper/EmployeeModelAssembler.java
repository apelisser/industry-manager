package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.EmployeeModel;
import com.apelisser.manager.domain.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeModelAssembler {

    private final ModelMapper mapper;

    public EmployeeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EmployeeModel toModel(Employee employee) {
        return mapper.map(employee, EmployeeModel.class);
    }

    public List<EmployeeModel> toCollectionModel(List<Employee> employees) {
        return employees.stream()
            .map(this::toModel)
            .toList();
    }

}
