package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CheckOnlineReplenishment {

    private WebDriver driver;

    public CheckOnlineReplenishment(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы элементов
    By blockTitle = By.xpath("//div[@class='pay__wrapper']//h2");
    By paymentLogos = By.xpath("//div[@class='pay__partners']");
    By serviceLink = By.xpath("//a[contains(text(),'Подробнее о сервисе')]");
    By payLogoSystem = By.xpath("//div[@class='cards-brands cards-brands__container ng-tns-c61-0 ng-trigger ng-trigger-brandsState ng-star-inserted']");



    public boolean isCookiePopupDisplayed() {
        try {
            return driver.findElement(By.xpath("//div[@class='cookie__wrapper']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void acceptCookiePopup() {
        WebElement cookieAgreeButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
        cookieAgreeButton.click();
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public String checkTitleName() {
         return driver.findElement(blockTitle).getText().replaceAll("\\s+", " ").trim();
    }

    public boolean areaLogoDisplay() {
        return driver.findElements(paymentLogos).size() > 0;
    }

    public boolean areaLogoDisplayFrame() {
        return driver.findElements(payLogoSystem).size() > 0;
    }


    //метод для проверки плейсхолдеров
    public List<String> getInputPlaceholders(String selectedOption) {
        List<String> placeholders = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//button[@class='select__header']")));

        WebElement selectButton = driver.findElement(
                By.xpath("//button[@class='select__header']"));
        selectButton.click();

        // Дожидаемся появления списка

        By selectOption = By.xpath(selectedOption);
        WebElement option = driver.findElement(selectOption);
        option.click();

        // Дожидаемся загрузки страницы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//form[@class='pay-form opened']")));


        WebElement form = driver.findElement(By.xpath("//form[@class='pay-form opened']"));
        List<WebElement> inputFields = form.findElements(By.cssSelector("input[placeholder]"));

        for (WebElement field : inputFields) {
            placeholders.add(field.getAttribute("placeholder"));
        }

        return placeholders;
    }

    public void fillServiceDetailsNext(WebDriver driver, String phone, String amount, String email) {
        try {
            WebElement inputPhone = driver.findElement(By.xpath("//input[@id='connection-phone']"));
            inputPhone.sendKeys(phone);
        } catch (NoSuchElementException e) {
            System.out.println("Элемент 'phone' не найден на странице");
        }

        try {
            WebElement inputAmount = driver.findElement(By.xpath("//input[@id='connection-sum']"));
            inputAmount.sendKeys(amount);
        } catch (NoSuchElementException e) {
            System.out.println("Элемент 'amount' не найден на странице");
        }

        try {
            WebElement inputEmail = driver.findElement(By.xpath("//input[@id='connection-email']"));
            inputEmail.sendKeys(email);
        } catch (NoSuchElementException e) {
            System.out.println("Элемент 'email' не найден на странице");
        }
    }

    public void clickServiceLink() {
        driver.findElement(serviceLink).click();
    }

}
