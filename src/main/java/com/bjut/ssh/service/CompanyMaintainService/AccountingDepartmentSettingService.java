package com.bjut.ssh.service.CompanyMaintainService;

import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.utils.Msg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountingDepartmentSettingService {
    public List<Departmentbase> getDepartments();

    Msg addDepartment(Departmentbase departmentbase);

    Msg updateDepartment(Departmentbase departmentbase);

    Msg deleteDepartment(String departmentId);

    Departmentbase queryDepartmentById(String departmentId);
}
