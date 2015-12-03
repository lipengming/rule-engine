
package com.cubbery;

public class Customer {
    boolean greeted;
    String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGreeted() {
        return greeted;
    }

    public void setGreeted(boolean greeted) {
        this.greeted = greeted;
    }
}
