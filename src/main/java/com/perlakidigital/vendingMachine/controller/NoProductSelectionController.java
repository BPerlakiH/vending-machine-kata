package com.perlakidigital.vendingMachine.controller;

import com.perlakidigital.vendingMachine.display.IDisplay;
import com.perlakidigital.vendingMachine.display.Message;

/**
 * A controller for the case when no product has been selected yet
 * Created by BPerlakiH on 15/05/2017.
 */
public class NoProductSelectionController extends AbstractController {

    public NoProductSelectionController(IDisplay display, IOutputDelegate delegate) {
        super(display, delegate);
    }

    @Override
    public void cancel() {
        display.clearScreen();
    }

    @Override
    public void selectShelf(int index) {
        // this should be not be reached at all
    }

    @Override
    public void insertCoin(Double coin) {
        delegate.onChange(coin);
        display.showMessage(Message.getSelectionRequired());
    }

}
