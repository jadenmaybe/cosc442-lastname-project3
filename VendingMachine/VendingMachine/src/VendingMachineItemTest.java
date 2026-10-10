import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VendingMachineItemTest {
    VendingMachineItem testVendingMachineItem;

    @BeforeEach
    void setUp() {
        testVendingMachineItem = new VendingMachineItem("testItem", 5.0);
    }

    @AfterEach
    void tearDown() {
        testVendingMachineItem = null;
    }

    @Test
    // Testing VendingMachineItem constructor with invalid case (price < 0)
    void testVendingMachineItem_InvalidPrice() {
        // act, assert
        assertThrows(VendingMachineException.class, () -> {
            new VendingMachineItem("test", -1.0);
        });
    }

    @Test
    void TestVendingMachineItem_ZeroPrice() {
        // act, assert
        assertEquals(0.0, new VendingMachineItem("test", 0.0).getPrice(), 0.001);
    }

    @Test
    void testGetName() {
        assertEquals("testItem", testVendingMachineItem.getName());
    }

    @Test
    void testGetPrice() {
        assertEquals(5.0, testVendingMachineItem.getPrice(), 0.001);
    }
}
