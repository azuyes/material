package com.bjut.ssh.controller.Login;

import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Warehouseset;
import com.bjut.ssh.service.Login.LoginService;
import com.bjut.ssh.utils.Msg;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

@RequestMapping("/UserManagement")
public class LoginController {
   @Autowired
   private SessionFactory sessionFactory;
   @Autowired
   private LoginService loginService;
   public Session getSession(){
        return this.sessionFactory.openSession();
    }

    public void close(Session session){
        if(session != null)
            session.close();
    }


    /**
     * 进入登录界面
     * @return 库房集合
     */
    @RequestMapping("/login")
    public @ResponseBody Msg enter(String userNo,String userPass) {
        return loginService.enter(userNo,userPass);
    }

    @RequestMapping("/getWarehouse")
    public @ResponseBody List<Warehouseset> getWarehouse() {
        return loginService.getWarehouse();
    }

}
