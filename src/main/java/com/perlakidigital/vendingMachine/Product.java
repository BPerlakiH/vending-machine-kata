package com.perlakidigital.vendingMachine;

/**
 * Product represents a buyable good with a non-negative price and a name
 * Created by BPerlakiH on 05/05/2017.
 */
class Product {

    private double price = 1.0;
    private String name = "";

    Product(String name) {
        setName(name);
    }

    Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    double getPrice() {
        return price;
    }

    void setPrice(double price) {
        assert (0 < price);
        this.price = price;
    }


}
