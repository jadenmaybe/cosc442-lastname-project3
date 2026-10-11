import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VendingMachineItemTest {

    @Test
    public void testValidItemCreationAtZeroPriceBoundary() {
        // Arrange
        String expectedName = "Free Item";
        double expectedPrice = 0.0;

        // Act
        VendingMachineItem item = new VendingMachineItem(expectedName, expectedPrice);

        // Assert
        assertEquals(expectedName, item.getName());
        assertEquals(expectedPrice, item.getPrice(), 0.001);
    }

    @Test
    public void testValidItemCreationStandardPrice() {
        // Arrange
        String expectedName = "Soda";
        double expectedPrice = 1.50;

        // Act
        VendingMachineItem item = new VendingMachineItem(expectedName, expectedPrice);

        // Assert
        assertEquals(expectedName, item.getName());
        assertEquals(expectedPrice, item.getPrice(), 0.001);
    }

    @Test
    public void testItemPriceJustBelowZeroThrowsException() {
        // Arrange
        String name = "Chips";
        double invalidPrice = -0.01;

        // Act & Assert
        assertThrows(VendingMachineException.class, () -> {
            new VendingMachineItem(name, invalidPrice);
        });
    }
}