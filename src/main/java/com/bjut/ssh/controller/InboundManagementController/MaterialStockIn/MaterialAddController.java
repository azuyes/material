package com.bjut.ssh.controller.InboundManagementController.MaterialStockIn;


import com.bjut.ssh.entity.Materialclass;
import com.bjut.ssh.entity.Materialsset;
import com.bjut.ssh.entity.Materialstockin;
import com.bjut.ssh.serviceImpl.InboundManagementServiceImpl.MaterialAddServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/InboundManagement")
@Controller
public class MaterialAddController {

    @Autowired
    private MaterialAddServiceImpl materialAddService;


    /**
     * @description 获取materialclass表中level相符合的所有数据
     * @param level
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMaterialClass/{level}")
    public List<Materialclass> getMaterialClass(@PathVariable("level") Integer level){
        return materialAddService.getMaterialClass(level);
    }


    /**
     * @description 获取materialclass表中id类似(即相同公司)的所有数据个数
     * @param id
     * @return
     */
    @RequestMapping(value = "/classSize/{id}",method= {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Integer classSize(@PathVariable("id") String id){
        return materialAddService.classSize(id);
    }


    /**
     * @desciption 本函数查找materialclass表中id和level符合的所有数据
     * @param id
     * @param level
     * @return 符合条件的数据列表
     */
    @ResponseBody
    @RequestMapping("/getAssetClassByIdAndLevel/{id}/{level}")
    public List<Materialclass> getAssetClassByIdAndLevel(@PathVariable("id")String id,@PathVariable("level")String level){
        return materialAddService.getAssetClassByIdAndLevel(id,level);
    }


    /**
     * @description 本函数根据传入的id，到materialset表中查找表中MaterialID字段前6位相同的条目
     * @param id 即物资类别，MaterialID中的前6位
     * @return 符合条件的数据集合
     * @author Su
     * @date 2019/7/15
     */
    @ResponseBody
    @RequestMapping("getMaterialDetailsById/{id}")
    public List<Materialsset> getMaterialDetailsById(@PathVariable("id")String id)
    {
        return materialAddService.getMaterialDetailsById(id);
    }


    /**
     * @description 添加物资明细时，调用本函数查询当前最后一个物资的编号是多少。目的是达到id自增的效果。
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("getMaxMaterialId/{id}")
    public List<String> getMaxMaterialId(@PathVariable("id")String id)
    {
        return materialAddService.getMaxMaterialId(id);
    }


    @ResponseBody
    @RequestMapping(value = "/addMaterialDetail",method= {RequestMethod.POST,RequestMethod.GET})
    public boolean addMaterialDetail(@RequestBody Materialstockin materialstockin)
    {
        return materialAddService.addMaterialDetail(materialstockin);
    }


    /**
     * @description 本函数读取MaterialStockIn表中所有数据
     * @author Su
     * @data 2019/7/17
     */
    @ResponseBody
    @RequestMapping("/getMaterialStockIn")
    public List<Materialstockin> getMaterialStockIn()
    {
        return materialAddService.getMaterialStockIn();
    }

}
