package com.perlakidigital.vendingMachine;

import java.util.LinkedList;

/**
 * A collection of shelves boxed into this class
 * Each shelf can contain only one type of product!
 * Created by BPerlakiH on 07/05/2017.
 */
class Shelves {

    private final LinkedList<LinkedList<Product>> shelves = new LinkedList<>();

    Shelves addShelf(String productName, double productPrice, int quantity) {
        LinkedList<Product> newShelf = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            newShelf.add(new Product(productName, productPrice));
        }
        shelves.add(newShelf);
        return this;
    }

    Boolean isEmptyAt(int index) throws IndexOutOfBoundsException {
        return shelves.get(index).isEmpty();
    }

    /**
     * Permanently removes a product from the given shelf, and returns it (or null, if shelf is empty)
     *
     * @return the top product or null, if shelf is empty
     */
    Product releaseAProductFrom(int index) throws IndexOutOfBoundsException {
        if (isEmptyAt(index)) {
            return null;
        }
        return shelves.get(index).remove(0);
    }

    /**
     * Returns (without removing) the top product from the given shelf (or null, if shelf is empty)
     *
     * @return the top product or null, if shelf is empty
     **/
    Product peakAtTopProductOn(int index) throws IndexOutOfBoundsException {
        if (isEmptyAt(index)) {
            return null;
        }
        return shelves.get(index).get(0);
    }

}
