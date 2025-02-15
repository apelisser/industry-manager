package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.model.Department;
import com.apelisser.manager.domain.repository.DepartmentRepository;
import com.apelisser.manager.domain.service.CompanyRegistrationService;
import com.apelisser.manager.domain.service.DepartmentRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentRegistrationServiceImpl implements DepartmentRegistrationService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRegistrationService companyService;

    public DepartmentRegistrationServiceImpl(DepartmentRepository departmentRepository,
        CompanyRegistrationService companyService) {
        this.departmentRepository = departmentRepository;
        this.companyService = companyService;
    }

    @Override
    public Department save(Department department) {
        String companyId = department.getCompany().getId();
        Company company = companyService.findById(companyId);
        department.setCompany(company);
        return departmentRepository.save(department);
    }

    @Override
    public void delete(String departmentId) {
        try {
            departmentRepository.deleteById(departmentId);
            departmentRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Department.class, departmentId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Department.class, departmentId, e);
        }
    }

    @Override
    public Department findById(String departmentId) {
        return departmentRepository.findById(departmentId)
            .orElseThrow(() -> new EntityNotFoundException(Department.class, departmentId));
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

}
