package com.bjut.ssh.controller.WarehouseOutController;

import com.bjut.ssh.entity.Materialstockout;
import com.bjut.ssh.service.WarehouseOutService.WarehouseOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/WarehouseOut")
public class WarehouseOutController {
    @Autowired
    WarehouseOutService warehouseOutService;

    @RequestMapping("/getWarehouseOut")
    @ResponseBody
    public List<Materialstockout> getWarehouseOut() {
        return warehouseOutService.getWarehouseOut();
    }
}
