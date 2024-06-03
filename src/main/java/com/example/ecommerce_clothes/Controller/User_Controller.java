package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Model.User;
import com.example.ecommerce_clothes.Service.User_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api/user")
@AllArgsConstructor
public class User_Controller {
    private final User_Service userService;

    @GetMapping("")
    public String viewUser(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam(defaultValue = "") String keyword) {
        if (keyword.isEmpty()) {
            model.addAttribute("list", userService.findAll());

        } else {
            model.addAttribute("list", userService.search(keyword, PageRequest.of(page, size)));
        }
        return "userlist";
    }

    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("actionForm", "/api/user/add");
        return "adduser";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("user") User user,
                      BindingResult bindingResult,
                      Model model
    ) throws ChangeSetPersister.NotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", userService.findAll());
            return "adduser";
        }
        userService.save(user);
        return "redirect:/api/user";
    }

    @GetMapping("/update/{id}")
    public String updateView(Model model, @PathVariable("id") Integer id) {
        Optional<User> userFind = userService.findById(id);
        if (userFind.isPresent()) {
            model.addAttribute("actionForm", "/api/user/update/" + id);
            model.addAttribute("user", userFind.get());
            return "adduser";
        } else {
            return "redirect:/api/user";
        }
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid @ModelAttribute("user") User user,
                         BindingResult bindingResult, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }
        try {
            userService.update(id, user);
        } catch (Exception e) {
            e.printStackTrace();
            return "adduser";
        }
        return "redirect:/api/user";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws ChangeSetPersister.NotFoundException {
        userService.removeOrRever(id);
        return "redirect:/api/user";
    }
}
