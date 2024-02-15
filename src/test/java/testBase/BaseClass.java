package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; // log4j
import org.apache.logging.log4j.Logger; //log4j

public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeTest(groups= {"regression", "master", "smoke"})
	@Parameters({"os", "browser"})
	public void setup(String os, String browser) throws IOException {
		FileReader fileReader = new FileReader(".//src/test/resources/config.properties");
		Properties properties = new Properties();
		properties.load(fileReader);
		
		//loading log4j2 file
		logger = LogManager.getLogger(this.getClass());
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			
			//setting operating System
			if(os.equalsIgnoreCase("windows")) {
				desiredCapabilities.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac")) {
				desiredCapabilities.setPlatform(Platform.MAC);
			}else {
				System.out.println("No Matching OS platform!!");
				return;
			}
			
			//Setting Browser
			if(browser.equalsIgnoreCase("chrome")) {			
				desiredCapabilities.setBrowserName("chrome");
			}else if(browser.equalsIgnoreCase("edge")) {
				desiredCapabilities.setBrowserName("MicrosoftEdge");
			}else {
				System.out.println("No Matching Browser");
				return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities);
		}else {
			//lunching browser based on condition - locally
			if(browser.equalsIgnoreCase("chrome")) {			
				driver = new ChromeDriver();
			}else if(browser.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			}else {
				System.out.println("No Matching Browser");
				return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("appUrl"));
		driver.manage().window().maximize();
		
		
	}
	
	@AfterTest(groups= {"regression", "master", "smoke"})
	public void tearDown() {
		driver.quit();
	}
	
	public static String screenshot(String name) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\screenshots\\"+name+" " +timeStamp+".png";
		File targetLocation = new File(path);
		FileUtils.copyFile(file, targetLocation);
		return path;
	}
	
}
