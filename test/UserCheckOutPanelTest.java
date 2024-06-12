import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import panes.UserCheckOutPanel;
import org.junit.Test;

public class UserCheckOutPanelTest {

    @Test
    public void testFindLeastFrequentSobarica() {
        // Create a list of sobarice
        List<String> sobarice = Arrays.asList("sobarica1", "sobarica1", "sobarica2", "soabrica3", "sobarica2", "sobarica1");

        // Call the method to find the least frequent sobarica
        String leastFrequentSobarica = UserCheckOutPanel.findLeastFrequentSobarica(sobarice);

        // Assert that the result is as expected
        assertEquals("soabrica3", leastFrequentSobarica);
    }

    @Test
    public void testFindLeastFrequentSobaricaWithTies() {
        // Create a list of sobarice with ties
        List<String> sobarice = Arrays.asList("sobarica1", "sobarica2", "soabrica3", "sobarica2", "soabrica3", "sobarica1");

        // Call the method to find the least frequent sobarica
        String leastFrequentSobarica = UserCheckOutPanel.findLeastFrequentSobarica(sobarice);

        // Assert that one of the expected results is returned (since there is a tie, either of the least frequent sobarice is acceptable)
        List<String> expectedResults = Arrays.asList("sobarica1", "sobarica2", "soabrica3");
        assertEquals(true, expectedResults.contains(leastFrequentSobarica));
    }

    @Test
    public void testFindLeastFrequentSobaricaEmptyList() {
        // Create an empty list of sobarice
        List<String> sobarice = Arrays.asList();

        // Call the method to find the least frequent sobarica
        String leastFrequentSobarica = UserCheckOutPanel.findLeastFrequentSobarica(sobarice);

        // Assert that the result is null for an empty list
        assertEquals(null, leastFrequentSobarica);
    }
}
