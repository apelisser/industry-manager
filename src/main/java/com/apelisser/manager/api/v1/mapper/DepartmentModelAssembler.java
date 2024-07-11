package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.DepartmentModel;
import com.apelisser.manager.domain.model.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentModelAssembler {

    private final ModelMapper mapper;

    public DepartmentModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public DepartmentModel toModel(Department department) {
        return mapper.map(department, DepartmentModel.class);
    }

    public List<DepartmentModel> toCollectionModel(List<Department> departments) {
        return departments.stream()
            .map(this::toModel)
            .toList();
    }

}
