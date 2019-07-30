package com.bjut.ssh.controller.MaterialPlan;

import com.bjut.ssh.service.MaterialPlan.MaterialPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

/**
 * 物资计划
 * @author wangqin
 * @Time 2019-7-27
 */
@RequestMapping("/MaterialPlan")
public class MaterialPlanController {
    @Autowired
    private MaterialPlanService materialPlanService;

    /**
     * 查询计划单
     * @param
     * @return 计划单列表
     */
    @RequestMapping("/Inquire")
    @ResponseBody
    public List Inquire() {
        return materialPlanService.Inquire();
    }
}
