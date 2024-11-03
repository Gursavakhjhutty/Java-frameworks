package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 *
 *
 *
 */
@Entity

@DiscriminatorValue("2")
public class OutsourcedPart extends Part {
    private String companyName;

    private int min; // Minimum inventory
    private int max; // Maximum inventory

    public OutsourcedPart() {

    }

    public OutsourcedPart(String name, double price, int inv, int min, int max, String companyName) {
        super(name, price, inv); // Call the superclass constructor if necessary
        this.min = min;
        this.max = max;
        this.companyName = companyName;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
