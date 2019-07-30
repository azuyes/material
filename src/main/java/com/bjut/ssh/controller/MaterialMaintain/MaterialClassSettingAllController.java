package com.bjut.ssh.controller.MaterialMaintain;

import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.service.MaterialMaintainService.MaterialClassSettingAllService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/MaterialClassSettingAll")
public class MaterialClassSettingAllController {

    @Autowired
    private MaterialClassSettingAllService materialClassSettingAllService;

    @RequestMapping("/queryMaterialClassByIdAndLevel/{materialClassId}/{materialClassLevel}")
    @ResponseBody
    public List<Materialclass> queryMaterialClassByIdAndLevel(@PathVariable("materialClassId") String materialClassId,@PathVariable("materialClassLevel") int materialClassLevel) {
        return materialClassSettingAllService.queryMaterialClassByIdAndLevel(materialClassId,materialClassLevel);
    }

    //通过输入两位编号和全局变量中的六位id计算出最终id然后添加
    @RequestMapping("/addMaterialClass/{currentId}")
    @ResponseBody
    public Msg addMaterialClass(@RequestBody Materialclass materialclass,@PathVariable("currentId") String currentId){
        if (materialclass.getMaterialClassLevel()==1){
            materialclass.setMaterialClassId(materialclass.getMaterialClassId()+"0000");
        }else if(materialclass.getMaterialClassLevel()==2){
            materialclass.setMaterialClassId(currentId.substring(0,2)+materialclass.getMaterialClassId()+"00");
        }else if (materialclass.getMaterialClassLevel()==3){
            materialclass.setMaterialClassId(currentId.substring(0,4)+materialclass.getMaterialClassId());
        }
        return materialClassSettingAllService.addMaterialClass(materialclass);
    }

    @RequestMapping("/updateMaterialClass/{currentId}")
    @ResponseBody
    public Msg updateMaterialClass(@RequestBody Materialclass materialclass,@PathVariable("currentId") String currentId){

        if (materialclass.getMaterialClassLevel()==1){
            materialclass.setMaterialClassId(materialclass.getMaterialClassId()+"0000");
        }else if(materialclass.getMaterialClassLevel()==2){
            materialclass.setMaterialClassId(currentId.substring(0,2)+materialclass.getMaterialClassId()+"00");
        }else if (materialclass.getMaterialClassLevel()==3){
            materialclass.setMaterialClassId(currentId.substring(0,4)+materialclass.getMaterialClassId());
        }

        return materialClassSettingAllService.updateMaterialClass(materialclass);

    }

    @RequestMapping("/deleteMaterialClass/{materialClassId}")
    @ResponseBody
    public Msg deleteMaterialClass(@PathVariable("materialClassId") String materialclassId){
        return materialClassSettingAllService.deleteMaterialClass(materialclassId);
    }
}