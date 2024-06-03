package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Model.Color;
import com.example.ecommerce_clothes.Model.Employee;
import com.example.ecommerce_clothes.Service.Employee_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api/employee")
@AllArgsConstructor
public class Employee_Controller{
    private final Employee_Service employeeService;

    @GetMapping("")
    public String viewList(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam(defaultValue = "") String keyword) {
        model.addAttribute("list", employeeService.findAll());
        return "employeelist";
    }

    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("actionForm", "/api/employee/add");
        return "addemployee";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) throws ChangeSetPersister.NotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", employeeService.findAll());
            return "addemployee";
        }
        employeeService.save(employee);
        return "redirect:/api/employee";
    }

    @GetMapping("/edit/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("actionForm", "/api/employee/edit/" + id);
            return "addemployee";
        } else {
            return "redirect:/api/employee";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id,
                         @Valid @ModelAttribute("employee") Employee employee,
                         Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "addemployee";
        }
        try {
            employeeService.update(id, employee);
        } catch (ChangeSetPersister.NotFoundException e) {
            model.addAttribute("errorMessage", "Employee not found with ID: " + id);
            return "addemployee";
        }
        return "redirect:/api/employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws ChangeSetPersister.NotFoundException {
        employeeService.removeOrRever(id);
        return "redirect:/api/employee";
    }
}
