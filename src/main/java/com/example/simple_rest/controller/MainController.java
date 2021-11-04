package com.example.simple_rest.controller;

import com.example.simple_rest.model.Employee;
import com.example.simple_rest.repository.EmployeeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "Управление работниками")
@RestController
@RequestMapping(value = "/api")
public class MainController {

    private final EmployeeRepository employeeRepository;

    public MainController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @ApiOperation("Получить информацию о конкретном работнике")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation("Вывод всех работников")
    @GetMapping("/all")
    public ResponseEntity<Page<Employee>> getAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> all = employeeRepository.findAll(pageable);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @ApiOperation("Создать работника")
    @PostMapping("/create")
    public ResponseEntity<Employee> post(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);

    }

    @ApiOperation("Обновить информацию о работнике")
    @PutMapping("/create")
    public ResponseEntity<Employee> put(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation("Удалить работника")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
