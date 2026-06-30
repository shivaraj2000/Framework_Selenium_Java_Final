package TestComponents;

import com.example.Landing_Page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public  WebDriver driver;
    public  Landing_Page lp;
    public void initializeWebDriver() throws IOException {

        Properties p= new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Resources/GlobalData.properties");
        p.load(fis);

        String browserName=p.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
             driver=new EdgeDriver();
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public Landing_Page launchApp() throws IOException {
        initializeWebDriver();
        lp= new Landing_Page(driver);
        lp.goTo();
        return lp;
    }

    @AfterMethod
    public  void tearDown()
    {
        driver.close();
    }
}
