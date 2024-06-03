package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Model.Size;
import com.example.ecommerce_clothes.Service.Size_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api/size")
@AllArgsConstructor
public class Size_Controller {
    private final Size_Service sizeService;

    @GetMapping("")
    public String viewList(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam(defaultValue = "") String keyword) {

        if (keyword.isEmpty()) {
            model.addAttribute("list", sizeService.findAll());

        } else {
            model.addAttribute("list", sizeService.search(keyword, PageRequest.of(page, size)));
        }
        return "sizelist";
    }

    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("size", new Size());
        model.addAttribute("actionFrom", "/api/size/add");
        return "addSize";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("size") Size size, BindingResult bindingResult, Model model) throws ChangeSetPersister.NotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", sizeService.findAll());
            return "addSize";
        }
        sizeService.save(size);
        return "redirect:/api/size";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Size> size = sizeService.findById(id);
        if (size.isPresent()) {
            model.addAttribute("size", size.get());
            model.addAttribute("actionForm","/api/size/edit/"+ id);
            return "addSize";  // Reuse the addSize.html for editing
        } else {
            return "redirect:/api/size";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateSize(@PathVariable("id") Integer id, @Valid @ModelAttribute("size") Size size, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addSize";
        }
        try {
            sizeService.update(id, size);
        } catch (ChangeSetPersister.NotFoundException e) {
            model.addAttribute("errorMessage", "Size not found");
            return "addSize";
        }
        return "redirect:/api/size";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws ChangeSetPersister.NotFoundException {
        sizeService.removeOrRever(id);
        return "redirect:/api/size";
    }
}
