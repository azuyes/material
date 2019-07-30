package com.bjut.ssh.serviceImpl.UsermanagementServiceImpl;

import com.bjut.ssh.dao.UserManagementDao.UserManagementDao;
import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.entity.Lsgnbh;
import com.bjut.ssh.entity.Lspass;
import com.bjut.ssh.entity.Lsusgn;
import com.bjut.ssh.service.UserManagementService.UserManagementService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserManagementDao userManagementDao;

    public Session getSession(){
        return this.sessionFactory.openSession();
    }

    public void close(Session session){
        if(session != null)
            session.close();
    }



    @Override
    @Transactional
    public List<Lspass> findUser() {
        return userManagementDao.findUser();
    }

    @Override
    public List<Lsgnbh> findFunctionAuthority(String menuNo) {
        return userManagementDao.findFunctionAuthority(menuNo);
    }

    @Override
    public String getAuthority(String userNo) {

        return userManagementDao.getAuthority(userNo);
    }

    @Override
    public void saveAuthority(String userNo,String FunctionAuthority){
        Lsusgn lsusgn = new Lsusgn();
        String[] functionAuthority = FunctionAuthority.split(",");
        Session session = getSession();
        Transaction ts = session.getTransaction();
        List<Lsusgn> lsusgnList = null;
        Query query = session.createQuery("from Lsusgn where userNo='"+userNo+"'");
        lsusgnList=query.list();
        try {
            ts.begin();
            //Lsusgn lsusgn1 = (Lsusgn)session.get(Lsusgn.class,userNo);
            if (lsusgnList.size()==0){
                for(int i=0;i<functionAuthority.length;i++){

                    lsusgn.setUserNo(userNo);
                    lsusgn.setMenuNo(functionAuthority[i]);
                    userManagementDao.saveAuthority(lsusgn);
                }
            }else {
                //先删除已有权限信息
                for(Lsusgn lsusgn1 : lsusgnList){
                    String id = lsusgn1.getUserNo();
                    userManagementDao.delAuthority(id);
                }
                //再加入新的权限信息
                for(int i=0;i<functionAuthority.length;i++) {

                    lsusgn.setUserNo(userNo);
                    lsusgn.setMenuNo(functionAuthority[i]);
                    System.out.println(functionAuthority[i]);
                    userManagementDao.saveAuthority(lsusgn);
                }
            }
            ts.commit();
            close(session);
        }catch (Exception e){
            e.printStackTrace();
            ts.rollback();
        }
    }

    @Override
    public List<Departmentbase> getDepartment(){
        return userManagementDao.getDepartment();
    }

    @Override
    public boolean addUser(Lspass lspass){
        return userManagementDao.addUser(lspass);
    }

    @Override
    public boolean delUser(String userNo){
        return userManagementDao.delUser(userNo);
    }
}
