package com.bjut.ssh.controller.CompanyMaintain;

import com.bjut.ssh.entity.Supplier;
import com.bjut.ssh.service.CompanyMaintainService.SupplierService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/getSuppliers")
    @ResponseBody
    public List<Supplier> getSuppliers(){
        return supplierService.getSuppliers();
    }

    @RequestMapping("/addSupplier")
    @ResponseBody
    public Msg saveSupplier(@RequestBody Supplier supplier){

        System.out.println("开始添加");
        return supplierService.addSupplier(supplier);

    }

    @RequestMapping("/updateSupplier")
    @ResponseBody
    public Msg updateSupplier(@RequestBody Supplier supplier){

        System.out.println("开始更新");
        return supplierService.updateSupplier(supplier);

    }

    @RequestMapping("/deleteSupplier/{supplierId}")
    @ResponseBody
    public Msg deleteSupplier(@PathVariable("supplierId") String supplierId){
        return supplierService.deleteSupplier(supplierId);
    }
}
