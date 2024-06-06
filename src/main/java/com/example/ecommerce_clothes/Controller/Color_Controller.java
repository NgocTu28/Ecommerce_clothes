package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Model.Color;
import com.example.ecommerce_clothes.Service.Color_Service;
import com.example.ecommerce_clothes.util.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api/color")
@AllArgsConstructor
public class Color_Controller {
    private final Color_Service colorService;
    private final SessionUtil session;
    @GetMapping("")
    public String viewList(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam(defaultValue = "") String keyword) {
        if (session.get() == null) {
            return "redirect:/api/login";
        }
        model.addAttribute("list", colorService.findAll());
        return "colorlist";
    }

    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("color", new Color());
        model.addAttribute("actionForm", "/api/color/add");
        return "addcolor";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("color") Color color, BindingResult bindingResult, Model model) throws ChangeSetPersister.NotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", colorService.findAll());
            return "addcolor";
        }
        colorService.save(color);
        return "redirect:/api/color";
    }

    @GetMapping("/edit/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        Optional<Color> color = colorService.findById(id);
        if (color.isPresent()) {
            model.addAttribute("color", color.get());
            model.addAttribute("actionForm", "/api/color/edit/" + id);
            return "addcolor";
        } else {
            return "redirect:/api/color";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id,
                         @Valid @ModelAttribute("color") Color color,
                         Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "addcolor";
        }
        try {
            colorService.update(id, color);
        } catch (ChangeSetPersister.NotFoundException e) {
            model.addAttribute("errorMessage", "Color not found with ID: " + id);
            return "addcolor";
        }
        return "redirect:/api/color";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws ChangeSetPersister.NotFoundException {
        colorService.removeOrRever(id);
        return "redirect:/api/color";
    }
}
