package com.Hospital.HospitalManagement.Service;

import com.Hospital.HospitalManagement.Entity.Department;
import com.Hospital.HospitalManagement.Entity.Doctor;
import com.Hospital.HospitalManagement.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public Department createDepartment(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new RuntimeException("Department with name '" + department.getName() + "' already exists.");
        }
        return departmentRepository.save(department);
    }


    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = getDepartmentById(id);

        if (departmentDetails.getName() != null && !departmentDetails.getName().equals(department.getName())) {
            if (departmentRepository.existsByName(departmentDetails.getName())) {
                throw new RuntimeException("Department with name '" + departmentDetails.getName() + "' already exists.");
            }
            department.setName(departmentDetails.getName());
        }

        if (departmentDetails.getHeadDoctor() != null) {
            department.setHeadDoctor(departmentDetails.getHeadDoctor());
        }

        if (departmentDetails.getDoctors() != null && !departmentDetails.getDoctors().isEmpty()) {
            Set<Doctor> doctors = departmentDetails.getDoctors();
            department.setDoctors(doctors);
        }

        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));
    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found with name " + name));
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}
