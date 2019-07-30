package com.bjut.ssh.dao.CompanyMaintainDao;

import com.bjut.ssh.entity.Customer;

import java.util.List;

public interface SellerDao {


    List<Customer> getCustomers();

    boolean addCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(String customerId);
}
