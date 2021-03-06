package MyPackages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.DataProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class FirstTest {

    WebDriver webDriver;

    @BeforeMethod
    public void SetUp() throws MalformedURLException, InterruptedException {

        //System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        //webDriver.manage().window().maximize();
        webDriver.get("https://www.google.com/");

    }

    @Test(priority = 3)
    public void Testcas1() throws InterruptedException {
        WebElement search = webDriver.findElement(By.id("small-searchterms"));

        WebElement Login = webDriver.findElement(By.className("ico-login"));
        //Login.click();

        //By EmailBy = with(By.tagName("input")).above(By.className("field-validation-valid"));

        //WebElement buttonSearch = webDriver.findElement(with(By.tagName("input")).toRightOf(By.id("small-searchterms")));

        //WebElement Email = webDriver.findElement(EmailBy);
        System.out.println(Login.getRect().getX() + "," + Login.getRect().getY() +
                ","+Login.getRect().getWidth() + ","+Login.getRect().getHeight());

        Thread.sleep(5000);

    }

    @Test(priority = 2)
    public void Testcas2() throws InterruptedException {
        //List<WebElement> Elements = webDriver.findElements(with(By.tagName("li")).near(By.id("color-squares-11")));
        List<WebElement>  ele = webDriver.findElements(By.xpath("//ul[@id='color-squares-11']/li/label/input"));
        //Thread.sleep(2000);
        //System.out.println(webDriver.findElement(By.xpath("//ul[@id='color-squares-11']")));

        System.out.println(ele.stream().count());
    }

    @Test(priority = 1)
    public void ScreenshotTest() throws IOException {
        //pour un ??l??ment
        WebElement TexteElement = webDriver.findElement(By.id("small-searchterms"));
        File TexteFile = TexteElement.getScreenshotAs(OutputType.FILE);
        File Destenation = new File("steps_dif.Search Texte.png");
        FileUtils.copyFile(TexteFile,Destenation);

        //pour la page web
        //File PageFile = (ChromeDriver)(webDriver.getFullPageShreenShotAs());
        //File Destenation2 = new File("Page.png");
        //FileUtils.copyFile(TexteFile,Destenation);
    }

    @Test(description = "My description :", priority = 4)
    public void TestCas3(){
        System.out.println("3");
    }


    ///// jeu de donn??e partie
    @Test(dataProvider = "UsersData")
    public void Login(String username, String Password) throws InterruptedException {
        WebElement Login = webDriver.findElement(By.className("ico-login"));
        Login.click();

        WebElement Email = webDriver.findElement(By.id("Email"));
        WebElement Pass = webDriver.findElement(By.id("Password"));

        Email.sendKeys(username);
        Pass.sendKeys(Password);
        Thread.sleep(2000);
    }

    @DataProvider(name = "UsersData")
    public Object[][] createData1() {
        return new Object[][] {
                { "wad.aiz@gmail.com", "Wadi1122" },
                { "sami@gmail.com", "Sqqq3322"},
        };
    }

    //////////

    @AfterMethod
    public void After(){
        webDriver.quit();
    }

}
