package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping("solutions")
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

    @GetMapping ("viewemployees")
    public Employee viewEmployee (@RequestParam ("id") int id) {
        Employee employee = employeeMap.get(id);
        return employee;
    }

    @PostMapping("")
    public void addEmployee(@RequestBody Employee employee) {
        employeeMap.put(id, employee);
        id++;
    }

    @GetMapping ("allemployees")
    public HashMap<Integer, Employee> viewAllEmployees () {
        return employeeMap;
    }
    @PutMapping("editemployee")
    public void editEmployee (@RequestBody Employee employee, @RequestParam("id") int id) {
        employeeMap.replace(id, employee);
    }

    @DeleteMapping("deleteemployee")
    public void deleteEmployee (@RequestParam("id") int id) {
        employeeMap.remove(id);
    }
}
