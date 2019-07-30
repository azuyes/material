package com.bjut.ssh.dao.UserManagementDao;

import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.entity.Lsgnbh;
import com.bjut.ssh.entity.Lspass;
import com.bjut.ssh.entity.Lsusgn;

import java.util.List;

public interface UserManagementDao {
    public List<Lspass> findUser();
    public List<Lsgnbh> findFunctionAuthority(String id);
    public String getAuthority(String userNo);

//     void saveAuthority(Lsusgn lsusgn);

    public void delAuthority(String id);
    public void saveAuthority(Lsusgn lsusgn);
    public List<Departmentbase> getDepartment();
    public boolean addUser(Lspass lspass);
    public boolean delUser(String userNo);
}
