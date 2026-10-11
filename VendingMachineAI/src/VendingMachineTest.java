import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void testInsertMoneyValidZeroBoundary() {
        // Arrange
        double amount = 0.0;

        // Act
        try {
            vendingMachine.insertMoney(amount);
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Assert
        assertEquals(amount, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testInsertMoneyValidPositive() {
        // Arrange
        double amount = 2.0;

        // Act
        try {
            vendingMachine.insertMoney(amount);
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Assert
        assertEquals(amount, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testInsertMoneyNegativeThrowsException() {
        // Arrange
        double invalidAmount = -1.0;

        // Act & Assert
        assertThrows(VendingMachineException.class, () -> {
            vendingMachine.insertMoney(invalidAmount);
        });
    }

    @Test
    public void testReturnChange() {
        // Arrange
        try {
            vendingMachine.insertMoney(5.0);
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act
        double change = vendingMachine.returnChange();

        // Assert
        assertEquals(5.0, change, 0.001);
        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testAllValidSlotBoundsAddAndGet() {
        // Arrange
        VendingMachineItem itemA = new VendingMachineItem("ItemA", 1.0);
        VendingMachineItem itemB = new VendingMachineItem("ItemB", 1.0);
        VendingMachineItem itemC = new VendingMachineItem("ItemC", 1.0);
        VendingMachineItem itemD = new VendingMachineItem("ItemD", 1.0);

        // Act & Assert for Slot A (index 0)[cite: 1]
        try {
            vendingMachine.addItem(itemA, "A");
            assertEquals(itemA, vendingMachine.getItem("A"));
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act & Assert for Slot B (index 1)[cite: 1]
        try {
            vendingMachine.addItem(itemB, "B");
            assertEquals(itemB, vendingMachine.getItem("B"));
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act & Assert for Slot C (index 2)[cite: 1]
        try {
            vendingMachine.addItem(itemC, "C");
            assertEquals(itemC, vendingMachine.getItem("C"));
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act & Assert for Slot D (index 3)[cite: 1]
        try {
            vendingMachine.addItem(itemD, "D");
            assertEquals(itemD, vendingMachine.getItem("D"));
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testAddDuplicateItemToOccupiedSlotThrowsException() {
        // Arrange
        VendingMachineItem item1 = new VendingMachineItem("Candy", 1.0);
        VendingMachineItem item2 = new VendingMachineItem("Gum", 0.5);
        try {
            vendingMachine.addItem(item1, "B");
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act & Assert
        assertThrows(VendingMachineException.class, () -> {
            vendingMachine.addItem(item2, "B");
        });
    }

    @Test
    public void testAddWithInvalidCodeThrowsException() {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Candy", 1.0);
        String invalidCode = "E"; // Outside bounds A-D[cite: 1]

        // Act & Assert
        assertThrows(VendingMachineException.class, () -> {
            vendingMachine.addItem(item, invalidCode);
        });
    }

    @Test
    public void testRemoveItem() {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Candy", 1.0);
        try {
            vendingMachine.addItem(item, "C");
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act
        VendingMachineItem removedItem = null;
        try {
            removedItem = vendingMachine.removeItem("C");
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Assert
        assertEquals(item, removedItem);
        try {
            assertNull(vendingMachine.getItem("C"));
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testRemoveItemFromEmptySlotThrowsException() {
        // Arrange
        String code = "D";

        // Act & Assert
        assertThrows(VendingMachineException.class, () -> {
            vendingMachine.removeItem(code);
        });
    }

    @Test
    public void testMakePurchaseExactFundsSuccess() {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Soda", 1.5);
        try {
            vendingMachine.addItem(item, "A");
            vendingMachine.insertMoney(1.5);
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act
        boolean purchaseResult = vendingMachine.makePurchase("A");

        // Assert
        assertTrue(purchaseResult);
        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
        try {
            assertNull(vendingMachine.getItem("A"));
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testMakePurchaseInsufficientFunds() {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Soda", 1.5);
        try {
            vendingMachine.addItem(item, "B");
            vendingMachine.insertMoney(1.0);
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act
        boolean purchaseResult = vendingMachine.makePurchase("B");

        // Assert
        assertFalse(purchaseResult);
        assertEquals(1.0, vendingMachine.getBalance(), 0.001);
        try {
            assertNotNull(vendingMachine.getItem("B"));
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testMakePurchaseEmptySlot() {
        // Arrange
        try {
            vendingMachine.insertMoney(5.0);
        } catch (VendingMachineException e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Act
        boolean purchaseResult = vendingMachine.makePurchase("C");

        // Assert
        assertFalse(purchaseResult);
    }
}