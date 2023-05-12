import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.List;

import static org.junit.Assert.assertTrue;

public class testUI {

        private WebDriver driver;
        JavascriptExecutor js;

        @Before
        public void setUp() {

            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Katya\\OneDrive\\Рабочий стол\\chromedriver_win32 (1)\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            js = (JavascriptExecutor) driver;

        }

        @After
        public void tearDown() {
            driver.quit();
        }

        @Test
        public void pageTest() {
            driver.get("https://playground.learnqa.ru/puzzle/triangle");
            List<WebElement> elements1 = driver.findElements(By.id("show_answ"));
            assertTrue(elements1.size() > 0);
            driver.findElement(By.id("show_answ")).click();
            List<WebElement> elements2 = driver.findElements(By.linkText("Ссылка на ответы"));
            assertTrue(elements2.size() > 0);
            List<WebElement> elements3 = driver.findElements(By.id("hide_answ"));
            assertTrue(elements3.size() > 0);
        }
    }

