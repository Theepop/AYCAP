package com.test.AYCAP.Entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="reference_code")
    private String referenceCode;
    @Column(name="Member_type")
    private String memberType;
    @Column(name="salary")
    private int salary;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }



}
