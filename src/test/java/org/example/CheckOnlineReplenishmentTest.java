package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


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

    @Step("Онлайн пополнение")
    @Test
    public void testCheckTitleName() {
        String title = replenishmentPage.checkTitleName();
        assertEquals("Онлайн пополнение без комиссии", title);
    }

    @Step("Проверка логотипов")
    @Test
    public void testAreaLogoDisplay() {
        boolean logoDisplayed = replenishmentPage.areaLogoDisplay();
        assertTrue(logoDisplayed);
    }


    @Step("Работа ссылки")
    @Test
    public void testClickServiceLink() {
        replenishmentPage.clickServiceLink();
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                "Не удалось перейти по ссылке");
        driver.navigate().back();
    }

    @Step("Кликабельность кнопки")
    @Test
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

    @Step("Поиск элементов во фрейме")
    @Test
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