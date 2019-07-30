package com.bjut.ssh.dao.CompanyMaintainDao;

import com.bjut.ssh.entity.Supplier;

import java.util.List;

public interface SupplierDao {

    List<Supplier> getSuppliers();

    boolean addSupplier(Supplier supplier);

    boolean updateSupplier(Supplier supplier);

    boolean deleteSupplier(String supplierId);
}
