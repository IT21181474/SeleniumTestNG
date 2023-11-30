package Tests;

import Pages.HomePage;
import Pages.ProductsPages;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SeleniumTest {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeTest
    public static void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://anupdamoda.github.io/AceOnlineShoePortal/index.html");
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Extent Reports Demo");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        HomePage.clickHamburgerMenu();
        HomePage.clickOnlineProductsLink();
    }

    @Test
    void validateTitles_OnlineProducts() {
        test = extent.createTest("Validate Shoe Titles on Products Page",
                "This test validates that the different Shoe types are correct on Online Products page");

        SoftAssert softAssert = new SoftAssert();

        try {
            ProductsPages.formalShoesVerifyTitle();
        } catch (AssertionError e) {
            test.fail("Validation failed for the Formal shoe.");
            softAssert.fail("Validation failed for the Formal shoe.");
        }

        try {
            ProductsPages.sportsShoesVerifyTitle();
        } catch (AssertionError e) {
            test.fail("Validation failed for the Sport shoe.");
            softAssert.fail("Validation failed for the Sport shoe.");
        }

        try {
            ProductsPages.sneakersVerifyTitle();
        } catch (AssertionError e) {
            test.fail("Validation failed for the Sneaker shoe.");
            softAssert.fail("Validation failed for the Sneaker shoe.");
        }

        // Mark the test as failed if any soft assert failed
        softAssert.assertAll();
    }
    @Test
    void validateFirstFormalShoes() {
        test = extent.createTest("Validate First Formal Shoe",
                "This test validates that the First Formal Shoe is correct on Online Products page");

        try {
            ProductsPages.formalShoesFirstShoeVerify();
            test.pass("Validation passed for the first sports shoe.");
        } catch (AssertionError e) {
            test.fail("Validation failed for the first sports shoe.");
            throw e; // This line ensures that the main test function fails if the validation fails
        }
    }


    @Test
    public void validateFirstSportsShoes() {
        test = extent.createTest("Validate First Sports Shoe", "This test validates First Sports Shoe on Online Products Page");
        try {
            ProductsPages.sportShoes_firstShoe_verify();
            test.pass("Validation passed for the first sports shoe.");
        } catch (AssertionError e) {
            test.fail("Validation failed for the first sports shoe.");
            throw e; // This line ensures that the main test function fails if the validation fails
        }
    }



    @AfterTest
    public static void cleanup() {
        extent.flush();
        driver.quit();
    }
}