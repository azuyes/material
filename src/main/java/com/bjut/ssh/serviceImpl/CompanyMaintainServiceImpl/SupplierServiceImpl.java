package com.bjut.ssh.serviceImpl.CompanyMaintainServiceImpl;

import com.bjut.ssh.dao.CompanyMaintainDao.SupplierDao;
import com.bjut.ssh.entity.Supplier;
import com.bjut.ssh.service.CompanyMaintainService.SupplierService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    @Override
    public List<Supplier> getSuppliers() {
        return supplierDao.getSuppliers();
    }

    @Override
    public Msg addSupplier(Supplier supplier){
        if (supplierDao.addSupplier(supplier)){
            return Msg.success();
        }else {
            Msg msg = new Msg();
            msg.setMsg("添加失败，请检供应商编号是否存在");
            return msg;
        }
    }

    @Override
    public Msg updateSupplier(Supplier supplier) {
        if (supplierDao.updateSupplier(supplier)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @Override
    public Msg deleteSupplier(String supplierId){
        if (supplierDao.deleteSupplier(supplierId)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
