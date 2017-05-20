package com.perlakidigital.vendingMachine.controller;

import com.perlakidigital.vendingMachine.Product;

/**
 * Interface for output behaviours
 * Created by BPerlakiH on 18/05/2017.
 */
public interface IOutputDelegate {

    void onChange(Double coin);

    void onProduct(Product product);

}
