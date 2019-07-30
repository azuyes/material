package com.bjut.ssh.serviceImpl.Helper;

import com.bjut.ssh.daoImpl.HelperDaoImpl.HelperDaoImpl;
import com.bjut.ssh.service.Helper.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class HelperServiceImpl implements HelperService {

    @Autowired
    private HelperDaoImpl helperDao;

    @Override
    public boolean isEnd(String id) {
        return helperDao.isEnd(id);
    }
}
