
package com.cubbery;

public class Product {
    String name;
    int count;

    public Product(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
