package com.bjut.ssh.serviceImpl.MaterialPlan;

import com.bjut.ssh.dao.MaterialPlan.MaterialPlanDao;
import com.bjut.ssh.service.MaterialPlan.MaterialPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

/**
 * 物资计划
 * @author wangqin
 * @Time 2019-7-27
 */
public class MaterialPlanServiceImpl implements MaterialPlanService {
    @Autowired
    private MaterialPlanDao materialPlanDao;

    /**
     * 查询计划单
     * @param
     * @return 计划单列表
     */
    @Override
    public List Inquire() {
        return materialPlanDao.Inquire();
    }
}
