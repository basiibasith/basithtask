package vendoliteTask;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class VendoliteLogin {
	static WebDriver driver;
	By username = By.name("username");
	By password = By.name("password");
	By submitLogin = By.xpath("//span[text()=' Login ']");
	By verifyLogin = By.xpath("//span[text()='Welcome, login user']");
	By verifyErrorMessage = By.xpath("//span[text() = 'Error Logging in : COMPANY_LOGGIN_FAILED']");

	public static void main(String[] args) {

		VendoliteLogin login = new VendoliteLogin();
		login.launchBrowser();
		login.loginWithvalidCredentials();
		login.quitBrowser();

		login.launchBrowser();
		login.loginWithInvalidUsername();
		login.quitBrowser();

		login.launchBrowser();
		login.loginWithInvalidPassword();
		login.quitBrowser();

		login.launchBrowser();
		login.loginWithBothInvalidCredentials();
		login.quitBrowser();
	}

	public void launchBrowser() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://cloud-test.vendolite.com/home/login");

	}

	public void loginWithvalidCredentials() {

		driver.findElement(username).sendKeys("loginuser@riota.in");
		driver.findElement(password).sendKeys("12345678");
		driver.findElement(submitLogin).click();
		WebElement successMessage = driver.findElement(verifyLogin);

		if (successMessage.isDisplayed()) {
			System.out.println("Login sucessfully " + successMessage.getText());
		}
	}

	public void loginWithInvalidUsername() {

		driver.findElement(username).sendKeys("loginuser@riota.com");
		driver.findElement(password).sendKeys("12345678");
		driver.findElement(submitLogin).click();
		WebElement errorMessage = driver.findElement(verifyErrorMessage);
	
		if (errorMessage.isDisplayed()) {
			System.out.println("Login failed " + errorMessage.getText());
		}
	}

	public void loginWithInvalidPassword() {

		driver.findElement(username).sendKeys("loginuser@riota.in");
		driver.findElement(password).sendKeys("login@123");
		driver.findElement(submitLogin).click();
		WebElement errorMessage = driver.findElement(verifyErrorMessage);

		if (errorMessage.isDisplayed()) {
			System.out.println("Login failed " + errorMessage.getText());
		}
	}

	public void loginWithBothInvalidCredentials() {

		driver.findElement(username).sendKeys("loginuser@riota.com");
		driver.findElement(password).sendKeys("login@123");
		driver.findElement(submitLogin).click();
		WebElement errorMessage = driver.findElement(verifyErrorMessage);

		if (errorMessage.isDisplayed()) {
			System.out.println("Login failed " + errorMessage.getText());
		}
	}

	public void quitBrowser() {

		driver.quit();
	}
}