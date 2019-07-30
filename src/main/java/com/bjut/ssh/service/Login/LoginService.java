package com.bjut.ssh.service.Login;

import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Warehouseset;
import com.bjut.ssh.utils.Msg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoginService {
    public Msg enter(String userNo, String userPass);
    public List<Warehouseset> getWarehouse();
}
