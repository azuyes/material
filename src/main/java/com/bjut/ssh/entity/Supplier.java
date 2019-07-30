package com.bjut.ssh.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Supplier {
    private String supplierId;
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
    @Column(name = "SupplierID")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
        Supplier supplier = (Supplier) o;
        return Objects.equals(supplierId, supplier.supplierId) &&
                Objects.equals(companyName, supplier.companyName) &&
                Objects.equals(address, supplier.address) &&
                Objects.equals(trustId, supplier.trustId) &&
                Objects.equals(bankName, supplier.bankName) &&
                Objects.equals(accountNo, supplier.accountNo) &&
                Objects.equals(phoneNo, supplier.phoneNo) &&
                Objects.equals(mobileNo, supplier.mobileNo) &&
                Objects.equals(regCapital, supplier.regCapital) &&
                Objects.equals(legalRep, supplier.legalRep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, companyName, address, trustId, bankName, accountNo, phoneNo, mobileNo, regCapital, legalRep);
    }
}
