package com.bjut.ssh.controller.MaterialMaintenance;

import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.service.MaterialMaintenance.MaterialSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

/**
 * 物资设置
 * @author wangqin
 * @Time 2019-7-9
 */
@RequestMapping("/MaterialSetting")
public class MaterialSettingController {
    @Autowired
    private MaterialSettingService materialSettingService;

    /**
     * 根据物资类别级数查询物资类别信息
     * @param i 物资类别级数
     * @return 物资类别信息列表
     */
    @RequestMapping("/Inquire")
    @ResponseBody
    public List<Materialclass> Inquire(int i) {
        return materialSettingService.Inquire(i);
    }

    /**
     * 添加物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确添加物资类别信息
     */
    @RequestMapping("/Add")
    @ResponseBody
    public boolean Add(@RequestBody Materialclass materialclass) {
        return materialSettingService.Add(materialclass);
    }

    /**
     * 修改物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确修改物资类别信息
     */
    @RequestMapping("/Edit")
    @ResponseBody
    public boolean Edit(@RequestBody Materialclass materialclass) {
        return materialSettingService.Edit(materialclass);
    }

    /**
     * 删除物资类别信息
     * @param materialclass 物资类别信息
     * @return 是否正确删除物资类别信息
     */
    @RequestMapping("/Remove")
    @ResponseBody
    public boolean Remove(@RequestBody Materialclass materialclass) {
        return materialSettingService.Remove(materialclass);
    }
}
