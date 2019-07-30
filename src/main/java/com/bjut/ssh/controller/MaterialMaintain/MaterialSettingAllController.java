package com.bjut.ssh.controller.MaterialMaintain;

import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.service.MaterialMaintainService.MaterialSettingAllService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/MaterialSettingAll")
public class MaterialSettingAllController {

    @Autowired
    private MaterialSettingAllService materialSettingAllService;

    @RequestMapping("/queryMaterialsByClassId/{materialClassId}")
    @ResponseBody
    public List<Materialsset> queryMaterialsByClassId(@PathVariable("materialClassId") String materialClassId){
        return materialSettingAllService.queryMaterialsByClassId(materialClassId);
    }

    @RequestMapping("/getMaxMaterialId/{materialClassId}")
    @ResponseBody
    public String getMaxMaterialId(@PathVariable("materialClassId") String materialClassId){
        return materialSettingAllService.getMaxMaterialId(materialClassId);
    }

    @RequestMapping("/addMaterial")
    @ResponseBody
    public Msg addMaterial(@RequestBody Materialsset materialsset){

        return materialSettingAllService.addMaterial(materialsset);

    }

    @RequestMapping("/updateMaterial")
    @ResponseBody
    public Msg updateMaterial(@RequestBody Materialsset materialsset){

        return materialSettingAllService.updateMaterial(materialsset);

    }

    @RequestMapping("/deleteMaterial/{materialId}")
    @ResponseBody
    public Msg deleteMaterial(@PathVariable("materialId") String materialId){
        return materialSettingAllService.deleteMaterial(materialId);
    }
}
