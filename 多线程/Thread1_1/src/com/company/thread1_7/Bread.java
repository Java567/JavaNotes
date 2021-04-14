package com.company.thread1_7;

import java.io.Serializable;

public class Bread implements Serializable {
    private int id;
    private String productName;

    public Bread(int id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public Bread() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Bread{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                '}';
    }
}
