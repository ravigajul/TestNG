package TestNG;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IManagement {
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
		driver.navigate().to("https://broadcomprd.service-now.com");
		System.out.println(driver.getTitle());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test//(dataProvider = "DataProvider")
	public void TestIncidentManagement(String FirstName, String LastName) {
		System.out.println("In Test Method");
		// First Name
		WebElement txtFirstName = driver.findElement(By.id("first-name"));
		txtFirstName.sendKeys(FirstName);

		
	}

	@AfterTest
	public void tearDown() {
		System.out.println("After Test Method");
		driver.close();
		driver.quit();
		
	}

	/*
	 * // @DataProvider(name = "DataProvider") // public Object[][]
	 * getDataFromDataprovider() { // return new Object[][] { { "Ravi", "Gajul" }, {
	 * "Krishna", "Chaitanya" }, { "Rajesh", "S" } }; // }
	 */}
