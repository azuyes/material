package com.bjut.ssh.daoImpl.CompanyMaintainDaoImpl;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.CompanyMaintainDao.SupplierDao;
import com.bjut.ssh.entity.Supplier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierDaoImpl implements SupplierDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private GeneralCRUD generalCRUD;

    @Override
    public List<Supplier> getSuppliers() {
        return generalCRUD.getMethod("Supplier");
    }

    @Override
    public boolean addSupplier(Supplier supplier){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(supplier);
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
    public boolean updateSupplier(Supplier supplier){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(supplier);
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
    public boolean deleteSupplier(String supplierId){
        return generalCRUD.delete("Supplier",supplierId);
    }
}
