package com.bjut.ssh.controller.UserManagement;


import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.entity.Lsgnbh;
import com.bjut.ssh.entity.Lspass;
import com.bjut.ssh.service.UserManagementService.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/UserManagement")
public class UserManagementController {
    @Autowired
    UserManagementService userManagementService;

    @RequestMapping("/findUser")
    public @ResponseBody List<Lspass> findUser(){
        List<Lspass> lspasses=userManagementService.findUser();
        return lspasses;
    }

    @RequestMapping("/findFunctionAuthority")
    @ResponseBody
    public List<Lsgnbh> findFunctionAuthority(String id){

        return userManagementService.findFunctionAuthority(id);
    }

    @RequestMapping("/getAuthority/{userNo}")
    @ResponseBody
    public String getAuthority(@PathVariable("userNo")String userNo){
        return userManagementService.getAuthority(userNo);
    }

    @RequestMapping("/saveAuthority/{userNo}/{FunctionAuthority}")
    @ResponseBody
    public String saveAuthority(@PathVariable("userNo")String userNo,@PathVariable("FunctionAuthority")String FunctionAuthority ){
        userManagementService.saveAuthority(userNo,FunctionAuthority);
        return "1";
    }

    @RequestMapping("/getDepartment")
    @ResponseBody
    public List<Departmentbase> getDepartment(){
        return userManagementService.getDepartment();
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser(@RequestBody Lspass lspass){
        return userManagementService.addUser(lspass);
    }

    @RequestMapping("/delUser/{userNo}")
    @ResponseBody
    public boolean delUser(@PathVariable("userNo") String userNo){
        return userManagementService.delUser(userNo);
    }
}
