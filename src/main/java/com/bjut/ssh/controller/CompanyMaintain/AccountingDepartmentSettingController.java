package com.bjut.ssh.controller.CompanyMaintain;

import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.service.CompanyMaintainService.AccountingDepartmentSettingService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/AccountingDepartment")
public class AccountingDepartmentSettingController {

    @Autowired
    private AccountingDepartmentSettingService accountingDepartmentSettingService;

    @RequestMapping("/getDepartments")
    @ResponseBody
    public List<Departmentbase> getDepartments(){
        return accountingDepartmentSettingService.getDepartments();
    }

    @RequestMapping("/addDepartment")
    @ResponseBody
    public Msg saveSupplier(@RequestBody Departmentbase departmentbase){

        return accountingDepartmentSettingService.addDepartment(departmentbase);

    }

    @RequestMapping("/updateDepartment")
    @ResponseBody
    public Msg updateSupplier(@RequestBody Departmentbase departmentbase){

        System.out.println("开始更新");
        return accountingDepartmentSettingService.updateDepartment(departmentbase);

    }

    @RequestMapping("/deleteDepartment/{departmentId}")
    @ResponseBody
    public Msg deleteDepartment(@PathVariable("departmentId") String departmentId){
        return accountingDepartmentSettingService.deleteDepartment(departmentId);
    }

}
