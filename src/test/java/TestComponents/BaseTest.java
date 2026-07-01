package TestComponents;

import com.example.Landing_Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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


    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

        //Reading the file to String
        String jsonContenet= FileUtils.readFileToString
                (new File(filepath), StandardCharsets.UTF_8);

        //Converting String to List with Hashmap using Jackson Bind
        ObjectMapper ob= new ObjectMapper();
        List<HashMap<String,String>> data= ob.readValue(jsonContenet, new TypeReference<List<HashMap<String, String>>>()
        {});
        return data;

    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
       TakesScreenshot ts= (TakesScreenshot) this.driver;
       File source=ts.getScreenshotAs(OutputType.FILE);
       File file=new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
       FileUtils.copyFile(source,file);
       return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun=true)
    public Landing_Page launchApp() throws IOException {
        initializeWebDriver();
        lp= new Landing_Page(driver);
        lp.goTo();
        return lp;
    }

    @AfterMethod(alwaysRun=true)
    public  void tearDown()
    {
        driver.close();
    }
}
