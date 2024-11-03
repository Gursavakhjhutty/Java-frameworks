package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 *
 *
 */
@Entity

@DiscriminatorValue("1")
public class InhousePart extends Part{
    private int partId;
    private int min;
    private int max;

    public InhousePart() {

    }

    public InhousePart(String name, double price, int inv, int min, int max, int partId) {
        super(name,price,inv);
        this.min = min;
        this.max = max;
        this.partId = partId;
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

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }
}
