package com.perlakidigital.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Testing various display values
 * Created by BPerlakiH on 06/05/2017.
 */
@RunWith(Parameterized.class)
public class DisplayTest {

    private final double input;
    private final String expected;
    private Display display;

    public DisplayTest(double inputValue, String expectedValue) {
        input = inputValue;
        expected = expectedValue;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
            new Object[][]{
                {1, "1.00"}, {2.3, "2.30"}, {3.01, "3.01"}, {99.99, "99.99"}, {-5.12, "-5.12"}
            }
        );
    }

    @Before
    public void setUp() throws Exception {
        display = new Display();
    }

    @After
    public void tearDown() throws Exception {
        display = null;
    }

    @Test
    public void testValues() {
        Assert.assertEquals("display should be empty by default", display.getMessage(), "");
        display.showPrice(input);
        Assert.assertEquals("should display the set price", display.getMessage(), expected);

        display.showOutOfStock();
        Assert.assertNotEquals("should show out of stock message", display.getMessage(), expected);
        Assert.assertTrue("should contain words 'out of stock'", display.getMessage().toLowerCase().contains("out of stock"));
        String outOfStockMsg = display.getMessage();

        display.showOutOfChange();
        Assert.assertNotEquals("should show out of change message", display.getMessage(), expected);
        Assert.assertNotEquals("should be different from out of stock message", display.getMessage(), outOfStockMsg);
        Assert.assertTrue("should contain words 'out of stock'", display.getMessage().toLowerCase().contains("out of change"));
    }
}
