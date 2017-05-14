package com.perlakidigital.vendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Calculates product purchases including the change that should be given back
 * Assuming that all change is picked up, before new coins are inserted
 * (it's a separation of concern, we calculate things up until that point and no further)
 * Created by BPerlakiH on 08/05/2017.
 */
public class Transaction {

    static final Collection<Double> DEFAULT_DENOMINATIONS = Arrays.asList(0.1, 0.2, 0.5, 1.0, 2.0, 5.0);
    private final ArrayList<Double> change;
    private final ArrayList<Double> coins;
    private final ArrayList<Double> till;
    private final Collection<Double> denominations;
    private double price;
    private double duePrice;
    private boolean isComplete = false;

    /**
     * @param coinsInTill  - A cash register that contains the coins we already have before the transaction
     * @param productPrice - The total price of the transaction / purchase
     */
    public Transaction(Collection<Double> coinsInTill, double productPrice) {
        denominations = DEFAULT_DENOMINATIONS;
        price = productPrice;
        duePrice = price;
        till = new ArrayList<>(coinsInTill);
        change = new ArrayList<>();
        coins = new ArrayList<>();
    }

    public void setProductPrice(double productPrice) {
        price = productPrice;
        processCoins();
    }

    /**
     * Get the remaining amount to be paid
     */
    public double getDuePrice() {
        return duePrice;
    }

    /**
     * @return the total price of the product
     */
    double getTotalPrice() {
        return price;
    }

    /**
     * Cancel the transaction, return the change if any
     */
    public void cancel() {
        change.addAll(coins);
        coins.clear();
        isComplete = false;
    }

    /**
     * Assuming that the change is "picked up" before new coins are inserted
     *
     * @return the change in coins
     */
    public Collection<Double> getChange() {
        return change;
    }

    /**
     * Get the current status of our till / cash register
     */
    ArrayList<Double> getTill() {
        return till;
    }

    public void addCoin(double value) {
        change.clear();
        if (isValidCoin(value)) {
            coins.add(value);
            processCoins();
        } else {
            //return the coin, it should fly through without stopping
            change.add(value);
        }
    }

    /**
     * A transaction is complete when the sum of inserted coins is equal (an exact amount) or above the price
     *
     * @return is enough coins inserted or not
     */
    public boolean isComplete() {
        return isComplete;
    }


    private void processCoins() {
        Double coinsTotal = getCoinsTotal();
        if (coinsTotal < price) {
            //the transaction is still ongoing, keep collecting coins, do nothing else
            duePrice = (price * 100 - coinsTotal * 100) / 100;
            isComplete = false;
            return;
        }
        if (coinsTotal == price) {
            //we got the exact amount, no change is necessary
            duePrice = 0;
            isComplete = true;
            till.addAll(coins);
            coins.clear();
            change.clear();
            return;
        }
        //we have overpayment, change is due:
        duePrice = 0;

        //check if we can give the rest from the all coins available:
        ArrayList<Double> allCoins = new ArrayList<>(coins);
        allCoins.addAll(till);
        CoinCounter counter = new CoinCounter(allCoins, coinsTotal - price);
        if (counter.isChangeAvailable()) {
            coins.clear();
            change.clear();
            change.addAll(counter.getChange());
            till.clear();
            till.addAll(counter.getRemainingCoins());
            isComplete = true;
        } else {
            //there's no way we can give back the change, roll back the transaction:
            cancel();
        }
    }

    /**
     * @return - the total value of coins inserted
     */
    private Double getCoinsTotal() {
        return coins.stream().reduce(0.0, Double::sum);
    }

    private boolean isValidCoin(double value) {
        return denominations.contains(value);
    }


}
