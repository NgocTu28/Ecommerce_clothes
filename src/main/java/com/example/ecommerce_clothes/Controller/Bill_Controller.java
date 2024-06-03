package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Model.Bill;
import com.example.ecommerce_clothes.Model.ProductDetail;
import com.example.ecommerce_clothes.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/bill")
@AllArgsConstructor
public class Bill_Controller {
    private final Bill_Service billService;
    private final BillDetail_Service billDetailService;
    private final ProductDetail_Service productDetailService;
    private final Employee_Service employeeService;
    private final User_Service userService;
    private final Product_Service productService;

    @GetMapping("")
    public String viewBill(Model model,
                           @RequestParam("product") Integer idProduct) {
        model.addAttribute("listBill", billService.findAll());
        model.addAttribute("listDetail", billDetailService.findAll());
        model.addAttribute("listEmployee", employeeService.findAll());
        model.addAttribute("listUser", userService.findAll());
        model.addAttribute("listProductDetail", productDetailService.findAll());
        model.addAttribute("billForm", new Bill());
        model.addAttribute("actionFormBill", "/api/bill/save");
        model.addAttribute("actionBillDetail", "/api/billdetail/save");
        return "sale";
    }

//    public String addBill(@Valid @ModelAttribute("billForm") Bill bill, BindingResult result, Model model) {
//        if(result.hasErrors()){
//            model.addAttribute("billForm", new Bill());
//            model.addAttribute("actionFormBill", "/api/bill/save");
//        }
//    }
}
