package com.bjut.ssh.controller.MaterialMaintain;

import com.bjut.ssh.entity.Warehouseset;
import com.bjut.ssh.service.MaterialMaintainService.WarehouseSettingService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/WarehouseSetting")
public class WarehouseSettingController {

    @Autowired
    private WarehouseSettingService warehouseSettingService;

    @RequestMapping("/getWarehouses")
    @ResponseBody
    public List<Warehouseset> getWarehouses(){
        return warehouseSettingService.getWarehouses();
    }

    @RequestMapping("/addWarehouse")
    @ResponseBody
    public Msg saveWarehouse(@RequestBody Warehouseset warehouseset){

        return warehouseSettingService.addWarehouse(warehouseset);

    }

    @RequestMapping("/updateWarehouse")
    @ResponseBody
    public Msg updateWarehouse(@RequestBody Warehouseset warehouseset){

        return warehouseSettingService.updateWarehouse(warehouseset);

    }

    @RequestMapping("/deleteWarehouse/{warehouseId}")
    @ResponseBody
    public Msg deleteWarehouse(@PathVariable("warehouseId") String warehouseId){
        return warehouseSettingService.deleteWarehouse(warehouseId);
    }
}
