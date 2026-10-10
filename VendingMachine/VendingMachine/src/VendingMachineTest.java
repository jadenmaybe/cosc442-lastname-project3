import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VendingMachineTest {
    VendingMachine myMachine;
    VendingMachineItem testItem;
    VendingMachineItem testItem2;

    @BeforeEach
    public void setUp() {
        myMachine = new VendingMachine();
        testItem = new VendingMachineItem("item", 5.0);
        testItem2 = new VendingMachineItem("item2", 8.0);
        myMachine.addItem(testItem, "A");
        myMachine.addItem(testItem2, "B");
    }

    @AfterEach
    public void tearDown() {
        myMachine = null;
    }

    @Test
    // Tests to see if addItem does not add an item at an already occupied slot
    void testAddItem_NotNullSlot() {
        // act, assert
        assertThrows(
                VendingMachineException.class, () -> myMachine.addItem(testItem2, "A"));
    }

    @Test
    // Tests to see if getBalance returns the correct balance
    void testGetBalance() {
        // act
        myMachine.insertMoney(5.0);
        // assert
        assertEquals(5.0, myMachine.getBalance(), 0.001);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "E", "F", "Z", "a", "d" })
    // Tests to see if getItem catches invalid codes
    void testGetItem_Invalid(String code) {
        // act, assert
        assertThrows(
                VendingMachineException.class, () -> myMachine.getItem(code));
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.25, 0.05, 1.0, 5.0, 0.0, 100 })
    // Tests if insertMoney() inserts the correct amount of money, passed in as
    // amount
    void testInsertMoney_Valid(double amount) {
        // act
        myMachine.insertMoney(amount);
        // assert
        assertEquals(amount, myMachine.getBalance(), 0.001);
    }

    @Test
    // Tests if insertMoney() catches invalid cases, being amounts < 0
    void testInsertMoney_Invalid() {
        // act, assert
        assertThrows(
                VendingMachineException.class, () -> myMachine.insertMoney(-5.0));
    }

    @Test
    // Test to see if makePurchase functions correctly with a valid case
    void testMakePurchase_Valid() {
        // act
        myMachine.insertMoney(5.0);
        // assert
        // makePurchase returns true if purchase is valid (appropriate amount of money
        // needed to make the purchase)
        assertEquals(true, myMachine.makePurchase("A"));
    }

    @Test
    // Test to see makePurchase correctly returns false with an invalid balance
    // (amount < purchased item price)
    void testMakePurchase_InvalidBalance() {
        // act
        myMachine.insertMoney(2.0);
        // assert
        // makePurchase returns true if purchase is valid (appropriate amount of money
        // needed to make the purchase). In this case, it should return false
        assertEquals(false, myMachine.makePurchase("A"));
    }

    @Test
    // Test to see if makePurchase correctly returns false if choosing to purchase
    // from a slot which holds null
    void testMakePurchase_NullItem() {
        // act
        myMachine.insertMoney(10.0);
        // assert
        // returns false if no item in the chosen slot
        assertEquals(false, myMachine.makePurchase("C"));
    }

    @Test
    // Test to see if removeItem correctly removes an item from the vending machine
    void testRemoveItem() {
        // act
        assertEquals(testItem, myMachine.getItem("A"));
        myMachine.removeItem("A");
        // assert
        assertEquals(null, myMachine.getItem("A"));
        // assert to check if nothing else but item at slot A was removed
        assertEquals(testItem2, myMachine.getItem("B"));
    }

    @Test
    // Test to see if removeItem correctly throws an exception at a null slot
    void testRemoveItem_Null() {
        // assert
        assertThrows(
                VendingMachineException.class, () -> myMachine.removeItem("D"));
    }

    @Test
    void testReturnChange() {
        // act
        myMachine.insertMoney(10.0);
        myMachine.returnChange();
        // assert
        assertEquals(0.0, myMachine.getBalance(), 0.001);

    }
}
