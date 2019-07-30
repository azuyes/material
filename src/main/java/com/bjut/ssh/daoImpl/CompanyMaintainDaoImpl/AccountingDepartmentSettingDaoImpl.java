package com.bjut.ssh.daoImpl.CompanyMaintainDaoImpl;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.CompanyMaintainDao.AccountingDepartmentSettingDao;
import com.bjut.ssh.entity.Departmentbase;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountingDepartmentSettingDaoImpl implements AccountingDepartmentSettingDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private GeneralCRUD generalCRUD;

    public Session getSession(){
        return this.sessionFactory.openSession();
    }

    public void close(Session session){
        if(session != null)
            session.close();
    }

    @Override
    public List<Departmentbase> getDepartments() {
        return generalCRUD.getMethod("Departmentbase");
    }

    @Override
    public boolean addDepartment(Departmentbase departmentbase){
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(departmentbase);
            tx.commit();
            return true;
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            close(session);
        }
    }

    @Override
    public boolean updateDepartment(Departmentbase departmentbase){
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(departmentbase);
            tx.commit();
            return true;
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
            return false;
        }finally {
            close(session);
        }
    }

    @Override
    public boolean deleteDepartment(String departmentId){
        return generalCRUD.delete("Departmentbase",departmentId);
    }

    @Override
    public Departmentbase queryDepartmentById(String departmentId){
        Session session = getSession();
        Transaction tx = null;
        Departmentbase departmentbase = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("from Departmentbase where departmentId = :depId");
            query.setParameter("depId",departmentId);
            Departmentbase dep = (Departmentbase) query.uniqueResult();
            tx.commit();
            return dep;
        }catch (RuntimeException e){
            tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            close(session);
        }
    }


}
