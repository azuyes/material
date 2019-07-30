package com.bjut.ssh.controller.helper;

import com.bjut.ssh.serviceImpl.Helper.HelperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/Helper")
@Controller
public class Helper {

    @Autowired
    private HelperServiceImpl helperService;


    @ResponseBody
    @RequestMapping("/isEnd/{id}")
    public boolean isEnd(@PathVariable("id") String id)
    {
        return helperService.isEnd(id);
    }
}
