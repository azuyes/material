package com.bjut.ssh.serviceImpl.CompanyMaintainServiceImpl;

import com.bjut.ssh.dao.CompanyMaintainDao.AccountingDepartmentSettingDao;
import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.service.CompanyMaintainService.AccountingDepartmentSettingService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingDepartmentSettingServiceImpl implements AccountingDepartmentSettingService {

    @Autowired
    private AccountingDepartmentSettingDao accountingDepartmentSettingDao;

    @Override
    public List<Departmentbase> getDepartments() {
        return accountingDepartmentSettingDao.getDepartments();
    }

    @Override
    public Msg addDepartment(Departmentbase departmentbase){
        if (accountingDepartmentSettingDao.addDepartment(departmentbase)){
            return Msg.success();
        }else {
            Msg msg = new Msg();
            msg.setMsg("添加失败，请检部门ID是否重复");
            return msg;
        }
    }

    @Override
    public Msg updateDepartment(Departmentbase departmentbase){
        if (accountingDepartmentSettingDao.updateDepartment(departmentbase)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @Override
    public Msg deleteDepartment(String departmentId){
        if (accountingDepartmentSettingDao.deleteDepartment(departmentId)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @Override
    public Departmentbase queryDepartmentById(String departmentId){
        return accountingDepartmentSettingDao.queryDepartmentById(departmentId);
    }

}
