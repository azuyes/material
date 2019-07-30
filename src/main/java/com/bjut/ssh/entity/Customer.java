package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Customer {
    private String customerId;
    private String companyName;
    private String address;
    private String trustId;
    private String bankName;
    private String accountNo;
    private String phoneNo;
    private String mobileNo;
    private Integer regCapital;
    private String legalRep;

    @Id
    @Column(name = "CustomerID")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "TrustID")
    public String getTrustId() {
        return trustId;
    }

    public void setTrustId(String trustId) {
        this.trustId = trustId;
    }

    @Basic
    @Column(name = "BankName")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Basic
    @Column(name = "AccountNo")
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Basic
    @Column(name = "PhoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Basic
    @Column(name = "MobileNo")
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Basic
    @Column(name = "RegCapital")
    public Integer getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(Integer regCapital) {
        this.regCapital = regCapital;
    }

    @Basic
    @Column(name = "LegalRep")
    public String getLegalRep() {
        return legalRep;
    }

    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(companyName, customer.companyName) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(trustId, customer.trustId) &&
                Objects.equals(bankName, customer.bankName) &&
                Objects.equals(accountNo, customer.accountNo) &&
                Objects.equals(phoneNo, customer.phoneNo) &&
                Objects.equals(mobileNo, customer.mobileNo) &&
                Objects.equals(regCapital, customer.regCapital) &&
                Objects.equals(legalRep, customer.legalRep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, companyName, address, trustId, bankName, accountNo, phoneNo, mobileNo, regCapital, legalRep);
    }
}
