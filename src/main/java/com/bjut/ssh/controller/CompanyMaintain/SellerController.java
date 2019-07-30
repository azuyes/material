package com.bjut.ssh.controller.CompanyMaintain;

import com.bjut.ssh.entity.Customer;
import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.service.CompanyMaintainService.AccountingDepartmentSettingService;
import com.bjut.ssh.service.CompanyMaintainService.SellerService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AccountingDepartmentSettingService accountingDepartmentSettingService;

    @RequestMapping("/getCustomers")
    @ResponseBody
    public List<Customer> getCustomers(){
        return sellerService.getCustomers();
    }

    @RequestMapping("/addCustomer")
    @ResponseBody
    public Msg saveCustomer(@RequestBody Customer customer){

        return sellerService.addCustomer(customer);

    }

    @RequestMapping("/updateCustomer")
    @ResponseBody
    public Msg updateCustomer(@RequestBody Customer customer){
        return sellerService.updateCustomer(customer);
    }


    @RequestMapping("/deleteCustomer/{customerId}")
    @ResponseBody
    public Msg deleteCustomer(@PathVariable("customerId") String customerId){
        return sellerService.deleteCustomer(customerId);
    }

}
