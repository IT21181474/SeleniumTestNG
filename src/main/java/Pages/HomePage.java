package Pages;

import org.openqa.selenium.By;

import static Tests.SeleniumTest.driver;

public class HomePage {
    public static String hamburgerMenuXpath = "//*[@id=\"menuToggle\"]/input";
    public static String onlineProductsLinkXpath = "//*[@id=\"menu\"]/a[3]/li";

    public static void clickHamburgerMenu() {
        driver.findElement(By.xpath(hamburgerMenuXpath)).click();
    }

    public static void clickOnlineProductsLink() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(onlineProductsLinkXpath)).click();
    }
}
