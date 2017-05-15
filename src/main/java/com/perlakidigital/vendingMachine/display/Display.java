package com.perlakidigital.vendingMachine.display;

/**
 * The display of the vending machine, supporting price and error messages
 * Created by BPerlakiH on 06/05/2017.
 */
class Display implements IDisplay {

    private static final String OUT_OF_STOCK = "Out of stock";
    private static final String OUT_OF_CHANGE = "Out of change";
    private static final String SELECTION_REQUIRED = "Please select a product first";
    private String message = "";

    /**
     * The default method to read the current message from the display
     *
     * @return the default message
     */
    public String getMessage() {
        return message;
    }

    public void showOutOfStock() {
        message = OUT_OF_STOCK;
    }

    public void showOutOfChange() {
        message = OUT_OF_CHANGE;
    }

    public void showSelectionRequired() {
        message = SELECTION_REQUIRED;
    }

    public void showPrice(double price) {
        message = String.format("%.2f", price);
    }

    public void clearScreen() {
        message = "";
    }
}
