package buscaSaldoXPI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

	private static WebDriver wd;
	private static int timeoutDefault = 5;

	private Driver() {
	}

	public static WebDriver getInstance() {
		if (wd == null) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Vitor\\Downloads\\UFSC\\Programação\\Selenium\\geckodriver-v0.13.0-win64\\geckodriver.exe");
			wd = new FirefoxDriver();
		}
		return wd;
	}

	public static WebElement getElementoComEspera(Localizador loc, int tempoMaximo) {
		By by = loc.getBy();
		WebDriverWait wait = new WebDriverWait(wd, tempoMaximo);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return wd.findElement(by);
	}
	
	public static WebElement getElementoComEspera(Localizador loc) {
		return getElementoComEspera(loc,timeoutDefault);
	}
	
	public static void delay(int milissegundos) {
		try {
			Thread.sleep(milissegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
