package com.bjut.ssh.dao.CompanyMaintainDao;

import com.bjut.ssh.entity.Departmentbase;

import java.util.List;

public interface AccountingDepartmentSettingDao {
    public List<Departmentbase> getDepartments();

    boolean addDepartment(Departmentbase departmentbase);

    boolean updateDepartment(Departmentbase departmentbase);

    boolean deleteDepartment(String departmentId);

    Departmentbase queryDepartmentById(String departmentId);
}
