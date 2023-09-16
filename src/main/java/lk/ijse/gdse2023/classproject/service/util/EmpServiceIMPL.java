package lk.ijse.gdse2023.classproject.service.util;

import lk.ijse.gdse2023.classproject.dto.EmployeeDTO;
import lk.ijse.gdse2023.classproject.entity.Employee;
import lk.ijse.gdse2023.classproject.repository.EmployeeRepository;
import lk.ijse.gdse2023.classproject.service.EmpService;
import lk.ijse.gdse2023.classproject.util.EntityDTOConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EmpServiceIMPL implements EmpService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EntityDTOConversion conversion;


    @Override
    public EmployeeDTO saveEmployeee(EmployeeDTO employeeDTO) {
        employeeDTO.setEmpID(UUID.randomUUID().toString());
        Employee empEntity = conversion.getEmpEntity(employeeDTO);
        employeeRepository.save(empEntity);
        return employeeDTO;
    }

    @Override
    public void deleteEmployee(String empId) {
       //Todo: to be created

    }

    @Override
    public void updateEmployee(String empID, EmployeeDTO employeeDTO) {
        //Todo: to be created
        Optional<Employee> tmpEmp = employeeRepository.findById(empID);
        //Validation
        if(tmpEmp.isPresent()) throw new RuntimeException("Employee not found");
        tmpEmp.get().setEmpName(employeeDTO.getEmpName());
        tmpEmp.get().setEmpDep(employeeDTO.getEmpDep());
        tmpEmp.get().setEmpEmail(employeeDTO.getEmpEmail());
        tmpEmp.get().setEmpProfile(employeeDTO.getEmpProfile());
    }

    @Override
    public EmployeeDTO getEmpbyId(String empID) {
        Employee employeeByEmpID = employeeRepository.findEmployeeByEmpID(empID);
        return conversion.getEmpDTO(employeeByEmpID);
    }
}
