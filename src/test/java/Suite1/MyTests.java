package Suite1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.Reporter;
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


public class MyTests {

    WebDriver webDriver;

    @BeforeMethod
    public void SetUp() throws MalformedURLException, InterruptedException {

        //System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        //webDriver.manage().window().maximize();
        webDriver.get("http://demowebshop.tricentis.com/");

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
        //pour un élément
        WebElement TexteElement = webDriver.findElement(By.id("small-searchterms"));
        File TexteFile = TexteElement.getScreenshotAs(OutputType.FILE);
        File Destenation = new File("steps_dif.Search Texte.png");
        FileUtils.copyFile(TexteFile,Destenation);

        //pour la page web
        //File PageFile = (ChromeDriver)(webDriver.getFullPageShreenShotAs());
        //File Destenation2 = new File("Page.png");
        //FileUtils.copyFile(TexteFile,Destenation);
    }

    @Test(description = "My description :", priority = 4,dataProvider = "SearchTexts")
    public void Search(String text) throws InterruptedException, IOException {
        Thread.sleep(2000);
        WebElement searchText = webDriver.findElement(By.id("small-searchterms"));
        searchText.sendKeys(text);

        WebElement searchBtn = webDriver.findElement(By.xpath("//body/div[4]/div[1]/div[1]/div[3]/form[1]/input[2]"));
        searchBtn.click();

        Thread.sleep(2000);
        WebElement CheckElement = webDriver.findElement(By.className("product-item"));

        File Pthoto = CheckElement.getScreenshotAs(OutputType.FILE);
        File Destenation = new File("Images/Check Texte.png");
        FileUtils.copyFile(Pthoto,Destenation);

        Assert.assertEquals(CheckElement.isDisplayed(),true);
    }


    ///// jeu de donnée partie
    @Test(dataProvider = "UsersData")
    public void Login(String username, String Password) throws InterruptedException {
        WebElement Login = webDriver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        Login.click();

        Thread.sleep(2000);
        WebElement Email = webDriver.findElement(By.id("Email"));
        WebElement Pass = webDriver.findElement(By.id("Password"));

        Email.sendKeys(username);
        Pass.sendKeys(Password);

        WebElement LoginBtn = webDriver.findElement(By.xpath("//body/div[4]/div[1]/div[4]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/form[1]/div[5]/input[1]"));
        LoginBtn.click();

        Thread.sleep(4000);
        WebElement Checkbtn = webDriver.findElement(By.xpath("//body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/a"));
        Assert.assertEquals(Checkbtn.getText(),username);
    }


    @DataProvider(name = "UsersData")
    public Object[][] createData1() {
        return new Object[][] {
                { "WAY@acial.fr", "Wade11223344" },
                { "sami@gmail.com", "Sqqq3322"},
        };
    }

    @DataProvider(name = "SearchTexts")
    public Object[][] SearchData() {
        return new Object[][] {
                { "Laptop"},
                { "Desktop"},
        };
    }

    //////////

    @AfterMethod
    public void After(){
        webDriver.quit();
    }

}
