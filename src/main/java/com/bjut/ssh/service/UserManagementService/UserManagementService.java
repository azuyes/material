package com.bjut.ssh.service.UserManagementService;

import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.entity.Lsgnbh;
import com.bjut.ssh.entity.Lspass;

import java.util.List;

public interface UserManagementService {
    public List<Lspass> findUser();
    public List<Lsgnbh> findFunctionAuthority(String id);
    public String getAuthority(String userNo);
    public void saveAuthority(String userNo,String FunctionAuthority);
    public List<Departmentbase> getDepartment();
    public boolean addUser(Lspass lspass);
    public boolean delUser(String userNo);
}
