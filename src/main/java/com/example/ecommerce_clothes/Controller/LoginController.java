package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Dto.LoginRequest;
import com.example.ecommerce_clothes.Model.Employee;
import com.example.ecommerce_clothes.Service.Employee_Service;
import com.example.ecommerce_clothes.util.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/shop")
@AllArgsConstructor
public class LoginController {
    private final SessionUtil sessionUtil;
    private final Employee_Service employeeService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("account", new LoginRequest());
        return "login";
    }

    @PostMapping("login")
    public String login(
            @Valid @ModelAttribute("account") LoginRequest request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("account", request);
            return "login";
        }

        Employee employee = employeeService.login(request.getUsername(), request.getPassword());
        if (employee == null) {
            model.addAttribute("message", "Tài khoản không tồn tại hoặc bị khóa");
            return "login";
        }
        sessionUtil.set(employee);
        return "redirect:/api/bill";
    }

    @GetMapping("logout")
    public String logout(Model model){
        sessionUtil.remove("account");
        return "redirect:/api/shop";
    }
}
