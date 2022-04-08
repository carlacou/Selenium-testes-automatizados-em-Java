package br.com.leilao.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWourldSelenium {
    @Test
    public void hello() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        //ChromeDriver driver = new ChromeDriver();
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/leiloes");
        browser.quit();
    }
}


