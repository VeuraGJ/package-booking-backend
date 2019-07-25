package com.oocl.packagebooking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PackageInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String cutomerName;
    private long telphoneNumber;
    private int status;
    private String orderTime;

    public PackageInformation() {
    }

    public PackageInformation(String cutomerName, long telphoneNumber, int status, String orderTime) {
        this.cutomerName = cutomerName;
        this.telphoneNumber = telphoneNumber;
        this.status = status;
        this.orderTime = orderTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public long getTelphoneNumber() {
        return telphoneNumber;
    }

    public void setTelphoneNumber(long telphoneNumber) {
        this.telphoneNumber = telphoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
