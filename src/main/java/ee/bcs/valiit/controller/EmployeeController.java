package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;

@RequestMapping("solution")
@RestController

public class EmployeeController {
    static HashMap<Integer, Employee> employeeMap = new HashMap<>();
    int id = 1;

    @GetMapping("createemployee")
    public Employee createEmployee(@RequestParam("name") String name, @RequestParam("age") int age) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employeeMap.put(id, employee);
        id++;
        return employee;
    }

    @GetMapping ("viewemployee")
    public Employee viewEmployee (@RequestParam("id") int id) {
        Employee employee = employeeMap.get(id);
        return employee;
    }

}
