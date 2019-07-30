package com.bjut.ssh.service.CompanyMaintainService;

import com.bjut.ssh.entity.Customer;
import com.bjut.ssh.utils.Msg;

import java.util.List;

public interface SellerService {

    List<Customer> getCustomers();

    Msg addCustomer(Customer customer);

    Msg updateCustomer(Customer customer);

    Msg deleteCustomer(String customerId);
}
