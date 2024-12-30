package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.DepartmentResumeModel;
import com.apelisser.manager.domain.model.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentResumeModelAssembler {

    private final ModelMapper mapper;

    public DepartmentResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public DepartmentResumeModel toModel(Department department) {
        return mapper.map(department, DepartmentResumeModel.class);
    }

    public List<DepartmentResumeModel> toCollectionModel(List<Department> departments) {
        return departments.stream()
            .map(this::toModel)
            .toList();
    }

}
