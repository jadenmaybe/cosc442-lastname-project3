import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VendingMachineItemTest {

    @Test
    public void testValidItemCreation() throws VendingMachineException {
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
    public void testItemPriceLessThanZeroThrowsException() {
        // Arrange
        String name = "Chips";
        double invalidPrice = -0.50;

        // Act & Assert
        assertThrows(VendingMachineException.class, () -> {
            new VendingMachineItem(name, invalidPrice);
        });
    }
}