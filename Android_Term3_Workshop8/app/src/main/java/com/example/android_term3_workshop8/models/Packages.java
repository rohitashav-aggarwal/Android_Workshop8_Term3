/*
Author - Rohit
Android - Term 3 Project
 */

package com.example.android_term3_workshop8.models;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Packages {

    private int packageId;

    private String pkgName;

    private Timestamp pkgStartDate;

    private Timestamp pkgEndDate;

    private String pkgDesc;

    private BigDecimal pkgBasePrice;

    private BigDecimal pkgAgencyCommission;


    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }


    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }


    public Timestamp getPkgStartDate() {
        return pkgStartDate;
    }

    public void setPkgStartDate(Timestamp pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }


    public Timestamp getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(Timestamp pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }


    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }


    public BigDecimal getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(BigDecimal pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }


    public BigDecimal getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    @NonNull
    @Override
    public String toString() {
        return getPkgName() + getPkgStartDate() + getPkgEndDate() + getPkgDesc() + getPkgBasePrice().toString();
    }
}
