package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Authentication_Alert {

    WebDriver driver;
    String username = "admin";
    String password = "admin";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }


    @Test
    public void TC_01_Authentication_Url() {

        // http/https:// + username + : + password + @ URL
        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),"Congratulations! You must have the proper credentials.");

    }

    @Test
    public void TC_02_Authentication() {

        driver.get("http://the-internet.herokuapp.com/");

        String basicLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenticationUrl(basicLink, username, password));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),"Congratulations! You must have the proper credentials.");

    }

    public String getAuthenticationUrl (String link, String username, String password) {
        String[] linkArray = link.split("//");
        return linkArray[0] + "//" + username + ":" + password + "@" + linkArray[1];
        }


    @AfterClass
    public void afterClass() {

        //driver.quit();
    }
}

