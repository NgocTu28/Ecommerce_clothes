package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Model.Product;
import com.example.ecommerce_clothes.Model.ProductDetail;
import com.example.ecommerce_clothes.Service.Color_Service;
import com.example.ecommerce_clothes.Service.ProductDetail_Service;
import com.example.ecommerce_clothes.Service.Product_Service;
import com.example.ecommerce_clothes.Service.Size_Service;
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
@RequestMapping("/api/productdetail")
@AllArgsConstructor
public class ProductDetail_Controller {
    private final ProductDetail_Service productDetailService;
    private final Product_Service productService;
    private final Color_Service colorService;
    private final Size_Service sizeService;

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable("id") Integer id, Model model){
        Optional<Product> product = productService.findById(id);
        List<ProductDetail> productDetails = productDetailService.findProductDetailByIdProduct(id);
        model.addAttribute("list",productDetails);
        model.addAttribute("product", product.orElse(null));
        return "productdetaillist";
    }

    @GetMapping("/add/{id}")
    public String viewAdd(Model model, @PathVariable("id") Integer id) {
        Optional<Product> product = productService.findById(id);
        model.addAttribute("productDetail", new ProductDetail());
        model.addAttribute("actionForm", "/api/productdetail/add");
        model.addAttribute("colorList", colorService.findAll());
        model.addAttribute("sizeList", sizeService.findAll());
        model.addAttribute("product", product.orElse(null));
        return "addproductdetail";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("productDetail") ProductDetail productDetail, BindingResult bindingResult) throws ChangeSetPersister.NotFoundException {
        if (bindingResult.hasErrors()) {
            System.out.println("Lỗi: " + bindingResult);
            return "addproductdetail";
        }
        productDetailService.save(productDetail);
        return "redirect:/api/productdetail/" + productDetail.getProduct().getId();
    }

    @GetMapping("/edit/{id}")
    public String viewEdit(@PathVariable("id") Integer id, Model model){
        Optional<ProductDetail> productDetailFind = productDetailService.findById(id);

        if(productDetailFind != null){
            model.addAttribute("productDetail", productDetailFind.get());
            model.addAttribute("actionform", "/api/productdetail/edit/"+id);
            model.addAttribute("colorList", colorService.findAll());
            model.addAttribute("sizeList", sizeService.findAll());
            model.addAttribute("product", productDetailFind.get().getProduct());
            return "addproductdetail";
        }else {
            return "redirect:/api/productdetail"+id;
        }
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute("productDetail") ProductDetail productDetail, Model model, BindingResult bindingResult, @PathVariable("id") Integer id){
        if (bindingResult.hasErrors()) {
            return "addproductdetail";
        }
        try {
            productDetailService.update(id, productDetail);
            return "redirect:/api/productdetail/"+id;
        } catch (ChangeSetPersister.NotFoundException e) {
            model.addAttribute("errorMessage", "ProductDetail not found with ID: " + id);
            return "addproductdetail";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProductDetail(@PathVariable("id") Integer id, Model model) throws ChangeSetPersister.NotFoundException {
        productDetailService.removeOrRever(id);
        return "redirect:/api/productdetail/"+id;
    }
}
