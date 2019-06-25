package TestNG;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDemo {
	WebDriver driver;
//additional comment
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"\\Users\\rgajul\\Documents\\Me\\elearning\\Ex_Files_Selenium_EssT\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Before Test Method");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://formy-project.herokuapp.com/form");
		System.out.println(driver.getTitle());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "DataProvider")
	public void TestFormy(String FirstName, String LastName) {
		System.out.println("In Test Method");
		// First Name
		WebElement txtFirstName = driver.findElement(By.id("first-name"));
		txtFirstName.sendKeys(FirstName);

		// Last Name
		WebElement txtLastName = driver.findElement(By.id("last-name"));
		txtLastName.sendKeys(LastName);

		// Radio
		WebElement rad = driver.findElement(By.xpath("//*[@id=\"radio-button-2\"]"));
		rad.click();

		// Checkbox
		WebElement cBox = driver.findElement(By.id("checkbox-2"));
		cBox.click();

		// DropDown

		Select drpYearsOfExp = new Select(driver.findElement(By.id("select-menu")));
		drpYearsOfExp.selectByVisibleText("0-1");
		drpYearsOfExp.selectByIndex(3);

		// Date Picker

		WebElement datePicker = driver.findElement(By.id("datepicker"));
		datePicker.click();
		WebElement prev = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[2]/th[1]"));
		prev.click();

		// navigating to home page.

		driver.get("https://formy-project.herokuapp.com/form");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void tearDown() {
		System.out.println("After Test Method");
		driver.close();
		driver.quit();
		
	}

	@DataProvider(name = "DataProvider")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "Ravi", "Gajul" }, { "Krishna", "Chaitanya" }, { "Rajesh", "S" } };
	}
}
