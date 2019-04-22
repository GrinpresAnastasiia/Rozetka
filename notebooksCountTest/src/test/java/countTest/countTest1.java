package countTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class countTest1 extends TestBase {

    @Test
    public void testCountDifferences() throws InterruptedException {
        clickOnBalances();
        int firstCount = getComparison();
        int secondCount = getOnlyDifferences();
        Assert.assertEquals(firstCount,secondCount);
    }
}
