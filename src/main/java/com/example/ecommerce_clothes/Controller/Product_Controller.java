package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Model.Product;
import com.example.ecommerce_clothes.Model.ProductDetail;
import com.example.ecommerce_clothes.Service.ProductDetail_Service;
import com.example.ecommerce_clothes.Service.Product_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/product")
@AllArgsConstructor
public class Product_Controller {
    private final Product_Service productService;
    private final ProductDetail_Service productDetailService;

    @GetMapping("")
    public String view(Model model,
                     @RequestParam(defaultValue = "0") int page,
                     @RequestParam(defaultValue = "5") int size,
                     @RequestParam(defaultValue = "") String keyword){
        model.addAttribute("list", productService.findAll());
        return "productlist";
    }

    @GetMapping("/add")
    public String viewAdd(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("actionForm", "/api/product/add");
        return "addproduct";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) throws ChangeSetPersister.NotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", productService.findAll());
            return "addproduct";
        }
        productService.save(product);
        return "redirect:/api/product";
    }

    @GetMapping("/edit/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("actionForm", "/api/product/edit/" + id);
            return "addproduct";
        } else {
            return "redirect:/api/color";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id,
                         @Valid @ModelAttribute("product") Product product,
                         Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "addproduct";
        }
        try {
            productService.update(id, product);
        } catch (ChangeSetPersister.NotFoundException e) {
            model.addAttribute("errorMessage", "Color not found with ID: " + id);
            return "addproduct";
        }
        return "redirect:/api/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws ChangeSetPersister.NotFoundException {
        productService.removeOrRever(id);
        return "redirect:/api/product";
    }

    @GetMapping("/productdetail/{id}")
    public String viewProductDetail(@PathVariable("id") Integer id, Model model){
        List<ProductDetail> productDetails = productDetailService.findProductDetailByIdProduct(id);
//        if(productDetails.isEmpty()){
//            model.addAttribute("list", productService.findAll());
//            return "redirect:/api/product";
//        }else {
            model.addAttribute("list",productDetails);
            return "redirect:/api/productdetail/"+id;
        }
    }

