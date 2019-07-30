package com.bjut.ssh.daoImpl.CompanyMaintainDaoImpl;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.CompanyMaintainDao.SellerDao;
import com.bjut.ssh.entity.Customer;
import com.bjut.ssh.entity.Departmentbase;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellerDaoImpl implements SellerDao {

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
    public List<Customer> getCustomers() {
        return generalCRUD.getMethod("Customer");
    }

    @Override
    public boolean addCustomer(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(customer);
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
    public boolean updateCustomer(Customer customer){

        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(customer);
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
    public boolean deleteCustomer(String customerId){
        return generalCRUD.delete("Customer",customerId);
    }

}
