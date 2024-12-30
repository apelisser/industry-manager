package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.input.DepartmentInput;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.model.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentInputDisassembler {

    private final ModelMapper mapper;

    public DepartmentInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Department toDomainObject(DepartmentInput departmentInput) {
        return mapper.map(departmentInput, Department.class);
    }

    public void copyToDomainObject(DepartmentInput departmentInput, Department department) {
        department.setCompany(new Company());
        mapper.map(departmentInput, department);
    }

}
