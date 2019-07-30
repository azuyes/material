package com.bjut.ssh.daoImpl.WarehouseOutDaoImpl;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.WarehouseOutDao.WarehouseOutDao;
import com.bjut.ssh.entity.Materialstockout;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public class WarehouseOutDaoImpl implements WarehouseOutDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    GeneralCRUD generalCRUD;

    public Session getSession(){
        return this.sessionFactory.openSession();
    }

    public void close(Session session){
        if(session != null)
            session.close();
    }


    @Override
    @RequestMapping("/getWarehouseOut")
    public List<Materialstockout> getWarehouseOut() {
        return generalCRUD.getMethod("Materialstockout");
    }
}
