package org.example;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CheckOnlineReplenishmentTest {

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

    @Test
    @DisplayName("Проверка заголовка")
    public void testCheckTitleName() {
        String title = replenishmentPage.checkTitleName();
        assertEquals("Онлайн пополнение без комиссии", title);
    }

    @Test
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void testAreaLogoDisplay() {
        boolean logoDisplayed = replenishmentPage.areaLogoDisplay();
        assertTrue(logoDisplayed);
    }


    @Test
    @DisplayName("Плейсхолдеры услуги связи")
    public void testGetInputPlaceholdersService() {
        List<String> placeholders = replenishmentPage.getInputPlaceholders(
                "//p[text()='Услуги связи']");
        assertEquals("Номер телефона", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Test
    @DisplayName("Плейсхолдеры домашний интернет")
    public void testGetInputPlaceholdersHomeInternet() {

        List<String> placeholders = replenishmentPage.getInputPlaceholders(
                "//p[text()='Домашний интернет']");
        assertEquals("Номер абонента", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Test
    @DisplayName("Плейсхолдеры рассрочка")
    public void testGetInputPlaceholdersCredit() {
        List<String> placeholders = replenishmentPage.getInputPlaceholders(
                "//p[text()='Рассрочка']");
        assertEquals("Номер счета на 44", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }

    @Test
    @DisplayName("Плейсхолдеры задолженность")
    public void testGetInputPlaceholdersDebt() {
        List<String> placeholders = replenishmentPage.getInputPlaceholders(
                "//p[text()='Задолженность']");
        assertEquals("Номер счета на 2073", placeholders.get(0));
        assertEquals("Сумма", placeholders.get(1));
        assertEquals("E-mail для отправки чека", placeholders.get(2));
    }


    @Test
    @DisplayName("Подробнее о сервисе")
    public void testClickServiceLink() {
        replenishmentPage.clickServiceLink();
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                "Не удалось перейти по ссылке");
        driver.navigate().back();
    }


    @Test
    @DisplayName("Проверка кнопки продолжить после заполнения")
    public void testFillServiceDetailsNext() {

        try {
            replenishmentPage.fillServiceDetailsNext(driver, getData.phone, getData.amount, getData.email);

            WebElement button = driver.findElement(By.xpath(
                    "//form[@id='pay-connection']//button[@type='submit']"));
            Assert.assertNotNull(button, "Элемент кнопки не найден на странице");
            button.click();
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден на странице");
        }
    }

    @Test
    @DisplayName("Проверка фрейма после заполнения формы")
    public void testFillServiceDetailsFrame() {
        try {
            replenishmentPage.fillServiceDetailsNext(driver, getData.phone, getData.amount, getData.email);

            WebElement button = driver.findElement(By.xpath(
                    "//form[@id='pay-connection']//button[@type='submit']"));
            Assert.assertNotNull(button, "Элемент кнопки не найден на странице");
            button.click();

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("iframe.bepaid-iframe")));

            driver.switchTo().frame(iframe);

            try {
                //цена в верху
                WebElement elementInsideIframe = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[contains(text(), 'BYN')]"))
                );
                String text = elementInsideIframe.getText();
                String titlePrice = text.replaceAll("[^0-9.]", "");


                //цена на кнопке
                WebElement buttonPrice = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//button[@class='colored disabled']")));
                String textButton = buttonPrice.getText();
                String buttonPriceString = textButton.replaceAll("[^0-9.]", "");

                // номер телефона
                WebElement numberString = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[contains(text(), 'Номер:')]")));
                String stringAndNumber = numberString.getText();
                String number = stringAndNumber.replaceAll("[^0-9.]", "");
                int length = number.length();
                if (length >= 9) {
                    String finishNumber = number.substring(length - 9);
                    assertEquals(finishNumber, getData.phone);
                }

                // лэйбел номера карты
                WebElement labelCard = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted']")));
                String card = labelCard.getText();

                // лэйбел действия карты
                WebElement labelTermCard = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']")));
                String termCard = labelTermCard.getText();

                // лэйбел CVC
                WebElement labelCvc = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted']")));
                String labelStringCvc = labelCvc.getText();


                // лэйбел держателя карты
                WebElement labelName = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']")));
                String nameString = labelName.getText();

                // платежные системы
                boolean logoDisplayedFrame = replenishmentPage.areaLogoDisplayFrame();
                assertTrue(logoDisplayedFrame);



                assertEquals(titlePrice, buttonPriceString);
                assertEquals(buttonPriceString, getData.amount);
                assertEquals(card, getData.labelNumberCard);
                assertEquals(termCard, getData.labelTernCard);
                assertEquals(labelStringCvc, getData.labelCvcCard);
                assertEquals(nameString, getData.labelNameCard);


            } catch (NoSuchElementException e) {
                System.out.println("Элемент не найден после ожидания");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Элемент не найден на странице");
        }
    }

}