package com.bjut.ssh.daoImpl.UserManagementDaoImpl;

import com.bjut.ssh.GeneralCRUD.GeneralCRUD;
import com.bjut.ssh.dao.UserManagementDao.UserManagementDao;
import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.entity.Lsgnbh;
import com.bjut.ssh.entity.Lspass;
import com.bjut.ssh.entity.Lsusgn;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public class UserManagementDaoImpl implements UserManagementDao {
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
    public List<Lspass> findUser() {
        return generalCRUD.getMethod("Lspass");
    }

    @Override
    public List<Lsgnbh> findFunctionAuthority(String menuNo){
        if(menuNo == null) {
            menuNo = "0";
        }
        return generalCRUD.queryByProperty("Lsgnbh","tid",menuNo);
    }

    @Override
    public String getAuthority(String userNo){
        Session session = null;
        Transaction ts = null;
        String FunctionAuthority = "";
        try {
            session=getSession();
            ts = session.beginTransaction();
            Query query = session.createQuery("from Lsusgn where userNo='"+userNo+"' order by menuNo desc");
            List<Lsusgn> lsusgnList = query.list();
            for (int i=0;i<lsusgnList.size();i++){
                String menuNo = lsusgnList.get(i).getMenuNo();
                if (FunctionAuthority != "")
                    FunctionAuthority += ",";
                FunctionAuthority += menuNo;
            }
            ts.commit();
            close(session);
        }catch (Exception e){
            e.printStackTrace();
            ts.rollback();
        }finally {
            return FunctionAuthority;
        }

    }

    @Override
    public void saveAuthority(Lsusgn lsusgn){
        generalCRUD.saveOrUpdate(lsusgn);
    }

    @Override
    public void delAuthority(String id){
        generalCRUD.delete("Lsusgn",id);
    }

    @Override
    public List<Departmentbase> getDepartment(){
        return generalCRUD.getMethod("Departmentbase");
    }

    @Override
    public boolean addUser(Lspass lspass){
        return generalCRUD.saveOrUpdate(lspass);
    }

    @Override
    public boolean delUser(String userNo){
        return generalCRUD.delete("Lspass",userNo);
    }
}
