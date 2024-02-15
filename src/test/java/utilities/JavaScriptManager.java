package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptManager {
	static JavascriptExecutor  jse;
	public static void scrollIntoView(WebDriver driver, WebElement element) {
		jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void scrollToTop(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0, 0);");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void highlightElement(WebDriver driver, WebElement element) {
		jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].style.border='2px solid red'", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void scrollInsideDiv(WebDriver driver, WebElement element) {
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView({behaviour: 'smooth', block: 'center'});", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
