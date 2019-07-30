package com.bjut.ssh.service.CompanyMaintainService;

import com.bjut.ssh.entity.Supplier;
import com.bjut.ssh.utils.Msg;

import java.util.List;

public interface SupplierService {
    List<Supplier> getSuppliers();

    Msg addSupplier(Supplier supplier);

    Msg updateSupplier(Supplier supplier);

    Msg deleteSupplier(String supplierId);
}
