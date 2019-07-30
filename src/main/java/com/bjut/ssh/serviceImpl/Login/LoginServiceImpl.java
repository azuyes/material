package com.bjut.ssh.serviceImpl.Login;

import com.bjut.ssh.dao.Login.LoginDao;
import com.bjut.ssh.entity.Lspass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Warehouseset;
import com.bjut.ssh.service.Login.LoginService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public Msg enter(String userNo, String userPass) {
        Lspass lspass=loginDao.enter(userNo,userPass);
        if(lspass==null){
            return Msg.fail();
        }
        String daoNo=lspass.getUserNo();
        String daoPass=lspass.getUserPass();
        String daoName=lspass.getUserName();
        if(daoNo.equals(userNo)&&daoPass.equals(userPass)){
            return Msg.success().add("isMatch",true).add("userName",daoName);
        }else{
            return Msg.success().add("isMatch",false);
        }

    }

    @Override
    public List<Warehouseset> getWarehouse() {
        return loginDao.getWarehouse();
    }
}
