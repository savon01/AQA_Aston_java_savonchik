package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckPlaceholdersTest {

    Data getData = new Data();

    protected WebDriver driver;
    private CheckOnlineReplenishment replenishmentPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\untitled3/AQA_Aston_java_savonchik/src/main/resources/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        replenishmentPage = new CheckOnlineReplenishment(driver);
        replenishmentPage.openPage(getData.getUrl);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (replenishmentPage.isCookiePopupDisplayed()) {
            replenishmentPage.acceptCookiePopup();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Проверка плейсхолдеров в услуги связи")
    @Test
    public void testGetInputPlaceholdersService() {
        List<String> placeholders = replenishmentPage.getInputPlaceholders(
                "//p[text()='Услуги связи']");
        assertEquals("Номер телефона", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Step("Проверка плейсхолдеров в Домашний интренет")
    @Test
    public void testGetInputPlaceholdersHomeInternet() {

        List<String> placeholders = replenishmentPage.getInputPlaceholders(
                "//p[text()='Домашний интернет']");
        assertEquals("Номер абонента", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Step("Проверка плейсхолдеров в Рассрочка")
    @Test
    public void testGetInputPlaceholdersCredit() {
        List<String> placeholders = replenishmentPage.getInputPlaceholders(
                "//p[text()='Рассрочка']");
        assertEquals("Номер счета на 44", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Step("Проверка плейсхолдеров в Задолжность")
    @Test
    public void testGetInputPlaceholdersDebt() {
        List<String> placeholders = replenishmentPage.getInputPlaceholders(
                "//p[text()='Задолженность']");
        assertEquals("Номер счета на 2073", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }
}
