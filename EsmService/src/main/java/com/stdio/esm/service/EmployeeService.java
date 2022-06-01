package com.stdio.esm.service;
import com.stdio.esm.model.Account;
import com.stdio.esm.model.Employee;
import com.stdio.esm.model.EmployeeSkills;
import com.stdio.esm.model.EmployeesProjects;
import com.stdio.esm.repository.AccountRepo;
import com.stdio.esm.repository.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeService {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private EmployeeRepo employeeRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public Map<String,Object> getUserDetails4() {
        Map<String,Object> responseData = new LinkedHashMap<>();
        Long id = AccountService.getCurrentAccountOrElseThrow().getId();
        Account account = accountRepo.findById(id).get();
        Employee employee = account.getEmployee();
        // Personal Details
        responseData.put("avatar_img",employee.getAvatar());
        responseData.put("name",employee.getName().toUpperCase());
        responseData.put("job_title",employee.getJobTitle());
        responseData.put("email",employee.getEmail());
        responseData.put("phone_number",employee.getPhoneNumber());
        responseData.put("address",employee.getAddress());
        responseData.put("place_of_birth",employee.getPlaceOfBirth());
        responseData.put("date_of_birth",employee.getDateOfBirth().toString().substring(0,10));
        responseData.put("nationality",employee.getNationality());
        responseData.put("gender",employee.getGender());
        responseData.put("website",employee.getWebsite());
        responseData.put("professional_summary",employee.getProfessionalSummary());
        //Employment History
        responseData.put("employment_history",employee.getEmploymentHistories());

        List<EmployeeSkills> employeeSkills;
        HashMap<Integer,String> employeeSkill= new HashMap<>();//Creating HashMap
        //key : level of skill, String skill 's name
        List<EmployeesProjects> employeesProjects;
        employeeSkills = employee.getEmployeeSkills();
        for(int i=0; i< employeeSkills.size(); i++) {
            employeeSkill.put(employeeSkills.get(i).getLevelOfSkill(),employeeSkills.get(i).getSkill().getName());  //Put elements in Map
        }
        List<String> projectName = new ArrayList<>();
        employeesProjects = employee.getEmployeesProjects();

        for(int i=0; i< employeesProjects.size(); i++) {
            projectName.add(employeesProjects.get(i).getProject().getName());
        }
        responseData.put("employee_skills",employeeSkill);
        responseData.put("employee_projects",projectName);
        return responseData;
    }
}

