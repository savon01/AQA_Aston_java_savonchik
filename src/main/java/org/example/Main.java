import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "D:\\untitled3/AQA_Aston_java_savonchik/src/main/resources/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://mts.by");

        List<String> placeholders = getInputPlaceholdersFromForm(driver);

        for (String placeholder : placeholders) {
            System.out.println(placeholder);
        }

        driver.quit();
    }

    public static List<String> getInputPlaceholdersFromForm(WebDriver driver) {
        List<String> placeholders = new ArrayList<>();

        WebElement form = driver.findElement(By.id("pay-connection"));
        List<WebElement> inputFields = form.findElements(By.cssSelector("input[placeholder]"));

        for (WebElement field : inputFields) {
            placeholders.add(field.getAttribute("placeholder"));
        }

        return placeholders;
    }
}