package com.perlakidigital.vendingMachine.display;

/**
 * Interface representing a general display approach
 * Created by BPerlakiH on 14/05/2017.
 */
public interface IDisplay {

    String getMessage();

    void showOutOfStock();

    void showOutOfChange();

    void showSelectionRequired();

    void showPrice(double price);

    void clearScreen();

}
