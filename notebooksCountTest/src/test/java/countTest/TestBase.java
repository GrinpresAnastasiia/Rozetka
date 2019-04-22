package countTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {
    static WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rozetka.com.ua");

        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

        getComputersSSD();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void getComputersSSD() {
        driver.findElement(By.cssSelector("[data-title='Ноутбуки и компьютеры']")).click();
        driver.findElement(By.cssSelector("[href='https://rozetka.com.ua/notebooks/c80004/']")).click();
        driver.findElement(By.cssSelector("[title='Ноутбуки с SSD']")).click();
    }

    public int getDifferencesCount() {
        String rowLocator = "//div[@class='comparison-t-row']";
        int rowsCounter = driver.findElements(By.xpath(rowLocator)).size();
        int counter = 0;
        for (int i = 1; i < rowsCounter; i++) {
            String name = driver.findElement(By.xpath(rowLocator + "[" + i + "]")).getAttribute("name");
            if (name.equalsIgnoreCase("different")) {
                counter++;
            }
        }
        return counter;
    }

    public void clickOnBalances() throws InterruptedException {
        Actions actions = new Actions(driver);
        List<WebElement> myElements = driver.findElements(By.cssSelector(".g-i-tile-catalog"));
        int counter=0;
        for(WebElement item : myElements) {
            actions.moveToElement(item).build().perform();
            Thread.sleep(3000);
            actions.moveToElement(driver.findElement(By.cssSelector("[title^='Добавить к']"))).click().build().perform();
            Thread.sleep(3000);
            counter++ ;
            if(counter==2) break;
        }
    }

    public int getOnlyDifferences() {
        driver.findElement(By.cssSelector("[href='#only-different']")).click();
        return driver.findElements(By.xpath("//div[@class='comparison-t-row']")).size();
    }

    public int getComparison() {
        driver.findElement(By.cssSelector(".hub-i.hub-i-comparison")).click();//переход к сравнению
        driver.findElement(By.cssSelector(".btn-link.btn-link-gray")).click();
        return getDifferencesCount();
    }
}
