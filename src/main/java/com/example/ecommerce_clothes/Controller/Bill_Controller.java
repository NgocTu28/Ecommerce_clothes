package com.example.ecommerce_clothes.Controller;

import com.example.ecommerce_clothes.Model.Bill;
import com.example.ecommerce_clothes.Model.BillDetail;
import com.example.ecommerce_clothes.Repository.BillDetail_Repository;
import com.example.ecommerce_clothes.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api/bill")
@AllArgsConstructor
public class Bill_Controller {
    private final Bill_Service billService;
    private final BillDetail_Service billDetailService;
    private final ProductDetail_Service productDetailService;
    private final Employee_Service employeeService;
    private final User_Service userService;
    private final BillDetail_Repository billDetailRepository;
    private final Product_Service productService;

    @GetMapping("")
    public String viewBill(Model model) {
        model.addAttribute("listBill", billService.findAllBillActive());
        model.addAttribute("listEmployee", employeeService.findAll());
        model.addAttribute("listUser", userService.findAll());
        model.addAttribute("billForm", new Bill());
        model.addAttribute("billDetailForm", new BillDetail());
        model.addAttribute("actionFormBill", "/api/bill/savebill");
        model.addAttribute("actionBillDetail", "/api/bill/billdetail/save");

        return "sale";
    }

    @PostMapping("/savebill")
    public String addBill(@Valid @ModelAttribute("billForm") Bill bill, BindingResult result, Model model) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()) {
            return "redirect:/api/bill";
        }
        model.addAttribute("billForm", new Bill());
        model.addAttribute("actionFormBill", "/api/bill/save");
        billService.save(bill);
        return "redirect:/api/bill";
    }

    @GetMapping("/editbill/{id}")
    public String viewEditBill(@PathVariable("id") Integer idBill, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("billForm", billService.findById(idBill));
        model.addAttribute("actionFormBill", "/api/bill/editbill/" + idBill);
        model.addAttribute("listEmployee", employeeService.findAll());
        model.addAttribute("listUser", userService.findAll());
        model.addAttribute("listBill", billService.findAllBillActive());
        model.addAttribute("actionBillDetail", "/api/bill/billdetail/save");
        model.addAttribute("billDetailForm", new BillDetail());
        Bill bill = billService.findById(idBill).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return "sale";
    }

    @PostMapping("/editbill/{id}")
    public String editBill(Model model, @Valid @ModelAttribute("bill") Bill billDto, BindingResult result, @PathVariable("id") Integer id) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()) {
            return "redirect:/api/bill";
        }
        billService.update(id, billDto);
        return "redirect:/api/bill";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws ChangeSetPersister.NotFoundException {
        billService.removeOrRever(id);
        return "redirect:/api/bill";
    }
//BillDetail.

    @GetMapping("/detail/{id}")
    public String getBillDetail(Model model, @PathVariable("id") Integer billId) throws ChangeSetPersister.NotFoundException {
        Bill bill = billService.findById(billId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        model.addAttribute("listBillDetail", billDetailService.findAllByBillId(billId));
        model.addAttribute("listBill", billService.findAllBillActive());
        model.addAttribute("listEmployee", employeeService.findAll());
        model.addAttribute("listUser", userService.findAll());
        model.addAttribute("listProductDetail", productDetailService.findAll());
        model.addAttribute("billDetailForm", new BillDetail());
        model.addAttribute("billForm", new Bill());
        model.addAttribute("actionFormBill", "/api/bill/save");
        model.addAttribute("actionBillDetail", "/api/bill/billdetail/save");
        model.addAttribute("billID", bill);
        return "sale";
    }

    @PostMapping("/billdetail/save")
    public String addBillDetail(Model model, @Valid @ModelAttribute("billDetailForm") BillDetail billDetail, BindingResult result) throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()) {
            return "redirect:/api/bill";
        }
        model.addAttribute("actionBillDetail", "/api/bill/billdetail/save");
        billDetailService.save(billDetail);

        return "redirect:/api/bill/detail/" + billDetail.getBill().getId();
    }

    @GetMapping("/detail/edit/{id}")
    public String editViewBillDetail(BillDetail billDetailDto, Model model, @PathVariable("id") Integer idBillDetail, BindingResult bindingResult) throws ChangeSetPersister.NotFoundException {
        Integer idBill = billDetailRepository.idBillSearchByIdBillDetail(idBillDetail);
        model.addAttribute("billDetailForm", billDetailService.findById(idBillDetail));
        model.addAttribute("listBillDetail", billDetailService.findAllByBillId(idBill));

        model.addAttribute("listBill", billService.findAllBillActive());
        model.addAttribute("listEmployee", employeeService.findAll());
        model.addAttribute("listUser", userService.findAll());
        model.addAttribute("listProductDetail", productDetailService.findAll());
        model.addAttribute("billForm", new Bill());
        model.addAttribute("actionFormBill", "/api/bill/save");
        model.addAttribute("actionBillDetail", "/api/bill/detail/edit/" + idBillDetail);
        return "sale";
    }

    @PostMapping("/detail/edit/{id}")
    public String editBillDetail(BillDetail billDetailDto, Model model, @PathVariable("id") Integer idBillDetail, BindingResult bindingResult) throws ChangeSetPersister.NotFoundException {
        Integer idBill = billDetailRepository.idBillSearchByIdBillDetail(idBillDetail);
        if (bindingResult.hasErrors()) {
            return "redirect:/api/bill";
        }
        BillDetail billDetail = billDetailService.findById(idBillDetail);
        if (billDetail.getStatic() != 0 && billDetail != null) {
            billDetailService.update(idBillDetail, billDetailDto);
        }
        return "redirect:/api/bill/detail/"+idBill;
    }
    @PostMapping("/detail/pay/{id}")
    public String payBill(Model model, @PathVariable("id") Integer idBill) throws ChangeSetPersister.NotFoundException {
        System.out.println(idBill);
        Optional<Bill> billFind = billService.findById(idBill);

        if (!billFind.isPresent()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Bill billSet = billFind.get();
        billSet.setStatus(0);
        billService.save(billSet);

        // Lấy danh sách hóa đơn và các thông tin khác để hiển thị lại
        model.addAttribute("listBill", billService.findAllBillActive());
        model.addAttribute("listEmployee", employeeService.findAll());
        model.addAttribute("listUser", userService.findAll());
        model.addAttribute("billForm", new Bill());
        model.addAttribute("billDetailForm", new BillDetail());
        model.addAttribute("actionFormBill", "/api/bill/savebill");
        model.addAttribute("actionBillDetail", "/api/bill/billdetail/save");

        // Chuyển hướng người dùng đến trang danh sách hóa đơn
        return "redirect:/api/bill";
    }

}
