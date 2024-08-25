package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;


public class CheckMtsByTest extends BaseTest {
    Data getData = new Data();

    @Test
    public void testCheckTitleName() {
        driver.get(getData.getUrl);

        WebElement blockTitle = driver.findElement(By.xpath("//div[@class='pay__wrapper']//h2"));
        Assert.assertNotNull(blockTitle, "Элемент не найден на странице");
        String actualTitle = blockTitle.getText().replaceAll("\\s+", " ").trim();
        String expectedTitle = "Онлайн пополнение без комиссии";
        Assert.assertEquals(actualTitle, expectedTitle, "Название не совпадает с ожидаемым");
        System.out.println("Название правильное");
    }

    @Test
    public void testCheckPaymentSystems() {
        driver.get(getData.getUrl);

        List<WebElement> paymentLogos = driver.findElements(By.xpath("//div[@class='pay__partners']"));

        Assert.assertNotNull(paymentLogos, "Логотипы платежных систем отсутствуют на странице");
        System.out.println("Логотипы платежных систем присутствуют на странице");
    }

    @Test
    public void testCheckingLinkFunctionality() {
        driver.get(getData.getUrl);


        WebElement serviceLink = driver.findElement(By.xpath("//a[contains(text(),'Подробнее о сервисе')]"));
        Assert.assertNotNull(serviceLink, "Ссылка «Подробнее о сервисе» не найдена на странице");

        serviceLink.click();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                "Не удалось перейти по ссылке");

        System.out.println("Ссылка работет!");
    }

    @Test
    public void testCheckContinueButton() {
        driver.get(getData.getUrl);

        WebElement inputPhone = null;
        try {
            inputPhone = driver.findElement(By.xpath("//input[@id='connection-phone']"));
            Assert.assertNotNull(inputPhone, "Элемент не найден на странице");
            inputPhone.sendKeys(getData.phone);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Элемент не найден на странице");
        }

        WebElement inputAmount = null;
        try {
            inputAmount = driver.findElement(By.xpath("//input[@id='connection-sum']"));
            Assert.assertNotNull(inputAmount, "Элемент не найден на странице");
            inputAmount.sendKeys(getData.amount);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Элемент не найден на странице");
        }

        WebElement inputEmail = null;
        try {
            inputEmail = driver.findElement(By.xpath("//input[@id='connection-email']"));
            Assert.assertNotNull(inputEmail, "Элемент не найден на странице");
            inputEmail.sendKeys(getData.email);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Элемент не найден на странице");
        }


        WebElement button = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
        Assert.assertNotNull(button, "Элемент не найден на странице");
        button.click();
    }
}