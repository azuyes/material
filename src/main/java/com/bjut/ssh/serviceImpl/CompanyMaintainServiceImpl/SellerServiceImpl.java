package com.bjut.ssh.serviceImpl.CompanyMaintainServiceImpl;

import com.bjut.ssh.dao.CompanyMaintainDao.SellerDao;
import com.bjut.ssh.entity.Customer;
import com.bjut.ssh.entity.Departmentbase;
import com.bjut.ssh.service.CompanyMaintainService.SellerService;
import com.bjut.ssh.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {


     @Autowired
     private SellerDao sellerDao;

     /**
      * 返回一个list，该list包含两个list的数据
      * 数据的元素有id，name和sellerType属性
      * sellerType表示该数据出自哪个表
      * 从departmentbase表中查询的sellerType 为department
      * 从customer表中查询的sellerType 为customer
      * @return
      */

     @Override
     public List<Customer> getCustomers() {
          return sellerDao.getCustomers();
     }

     @Override
     public Msg addCustomer(Customer customer){
          if (sellerDao.addCustomer(customer)){
               return Msg.success();
          }else {
               Msg msg = new Msg();
               msg.setMsg("添加失败，请检ID是否重复");
               return msg;
          }
     }


     @Override
     public Msg updateCustomer(Customer customer){
          if ( sellerDao.updateCustomer(customer) ){
               return Msg.success();
          }else {
               Msg msg = new Msg();
               msg.setMsg("修改失败，请检客户编号是否重复");
               return msg;
          }
     }

     @Override
     public Msg deleteCustomer(String customerId){
          if (sellerDao.deleteCustomer(customerId)){
               return Msg.success();
          }else {
               return Msg.fail();
          }
     }


}
