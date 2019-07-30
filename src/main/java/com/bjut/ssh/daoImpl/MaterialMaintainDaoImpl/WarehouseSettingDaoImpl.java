package com.bjut.ssh.daoImpl.MaterialMaintainDaoImpl;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.MaterialMaintainDao.WarehouseSettingDao;
import com.bjut.ssh.entity.Supplier;
import com.bjut.ssh.entity.Warehouseset;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WarehouseSettingDaoImpl implements WarehouseSettingDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GeneralCRUD generalCRUD;

    @Override
    public List<Warehouseset> getWarehouses(){
        return generalCRUD.getMethod("Warehouseset");
    }

    @Override
    public boolean updateWarehouse(Warehouseset warehouseset){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(warehouseset);
            tx.commit();
            return true;
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean addWarehouse(Warehouseset warehouseset){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(warehouseset);
            tx.commit();
            return true;
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteWarehouse(String warehouseId){
        return generalCRUD.delete("Warehouseset",warehouseId);
    }

}
