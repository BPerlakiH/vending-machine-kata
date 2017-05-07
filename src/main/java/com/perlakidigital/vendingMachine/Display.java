package com.perlakidigital.vendingMachine;

/**
 * The display of the vending machine, supporting price and error messages
 * Created by BPerlakiH on 06/05/2017.
 */
class Display {

    private static final String OUT_OF_STOCK = "Out of stock";
    private static final String OUT_OF_CHANGE = "Out of change";
    private String message = "";

    /**
     * The default method to read the current message from the display
     *
     * @return the default message
     */
    String getMessage() {
        return message;
    }

    void showOutOfStock() {
        message = OUT_OF_STOCK;
    }

    void showOutOfChange() {
        message = OUT_OF_CHANGE;
    }

    void showPrice(double price) {
        message = String.format("%.2f", price);
    }


}
