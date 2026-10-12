package game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;
    private Item dummyItem1;
    private Item dummyItem2;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(3);
        dummyItem1 = new Item('k', Color.YELLOW, "Key", "Golden Key");
        dummyItem2 = new Item('p', Color.BLUE, "Potion", "Blue Potion");
    }

    @AfterEach
    void tearDown() {
        inventory = null;
        dummyItem1 = null;
        dummyItem2 = null;
    }

    @Test
    void testGet_ItemAtIndex() {
        // Arrange
        inventory.add(dummyItem1);

        // Act
        Item retrievedItem = inventory.get(0);

        // Assert
        assertEquals(dummyItem1, retrievedItem);
    }

    @Test
    void testAdd_Item() {
        // Act
        inventory.add(dummyItem1);

        // Assert
        assertEquals(dummyItem1, inventory.get(0));
        assertNull(inventory.get(1));
    }

    @Test
    void testAdd_TilFull() {
        // Arrange
        Item item3 = new Item('s', Color.GREEN, "Scroll", "Ancient Scroll");

        // Act
        inventory.add(dummyItem1);
        inventory.add(dummyItem2);
        inventory.add(item3);

        // Assert
        assertEquals(dummyItem1, inventory.get(0));
        assertEquals(dummyItem2, inventory.get(1));
        assertEquals(item3, inventory.get(2));
        assertTrue(inventory.isFull());
    }

    @Test
    void testAdd_FullInventory() {
        // Arrange
        Item extraItem = new Item('c', Color.WHITE, "Coin", "Silver Coin");
        inventory.add(dummyItem1);
        inventory.add(dummyItem2);
        inventory.add(new Item('g', Color.MAGENTA, "Gem", "Ruby"));

        // Assert
        assertTrue(inventory.isFull());
        // Verify extra item was not added
        assertFalse(inventory.contains(extraItem));
    }

    @Test
    void testRemove_ExistingItem() {
        // Arrange
        inventory.add(dummyItem1);
        inventory.add(dummyItem2);
        assertTrue(inventory.contains(dummyItem1));

        // Act
        inventory.remove(dummyItem1);

        // Assert
        assertNull(inventory.get(0));
        assertFalse(inventory.contains(dummyItem1));
        assertEquals(dummyItem2, inventory.get(1));
    }

    @Test
    void testRemove_NonExistentItem() {
        // Arrange
        inventory.add(dummyItem1);

        // Act & Assert
        assertDoesNotThrow(() -> inventory.remove(dummyItem2));
        assertEquals(dummyItem1, inventory.get(0));
    }

    @Test
    void testIsFull_Empty() {
        // Act & Assert
        assertFalse(inventory.isFull());
    }

    @Test
    void testIsFull_NotFull() {
        // Arrange
        inventory.add(dummyItem1);

        // Act & Assert
        assertFalse(inventory.isFull());
    }

    @Test
    void testIsFull_True() {
        // Arrange
        inventory.add(dummyItem1);
        inventory.add(dummyItem2);
        inventory.add(new Item('o', Color.ORANGE, "Orange", "Orange"));

        // Act & Assert
        assertTrue(inventory.isFull());
    }

    @Test
    void testContains_True() {
        // Arrange
        inventory.add(dummyItem1);

        // Act & Assert
        assertTrue(inventory.contains(dummyItem1));
    }

    @Test
    void testContains_False() {
        // Act & Assert
        assertFalse(inventory.contains(dummyItem1));
    }

}