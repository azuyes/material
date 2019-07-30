package com.bjut.ssh.dao.Login;

import com.bjut.ssh.entity.Lspass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Warehouseset;

import java.util.List;

public interface LoginDao {
    public Lspass enter(String userNo, String userPass);
    public List<Warehouseset> getWarehouse();
}
