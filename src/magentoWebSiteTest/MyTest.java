package magentoWebSiteTest;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MyTest {
	
	
	
	WebDriver driver = new ChromeDriver();
	String mywebsite ="https://magento.softwaretestingboard.com/";
	String password="IloveCanada123!";
	String LogoutPage="https://magento.softwaretestingboard.com/customer/account/logout/";
	String SignInPage="https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";
	String emailAddressToLogin = "";

	Random rand = new Random();
	
	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		driver.get(mywebsite);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	
	}

	@Test(priority  =1)
	public void createAnAccount() {
		
		WebElement webSitepage= driver.findElement(By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']"));
		webSitepage.click();
		String SampleFirstName []= {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ian","Judy" };
		String SampleLastName []= { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson","Moore", "Taylor" };
        
		int  RandomIndexOffirstName = rand.nextInt(SampleFirstName.length); //.length -- static Array
		int RandomIndexOfLastName=rand.nextInt(SampleLastName.length);
		String FirstName = SampleFirstName[RandomIndexOffirstName];
		String lastName=SampleLastName[RandomIndexOfLastName];
		int RandomNumber=rand.nextInt(9876);
		String domainName="@gmil.com";
		System.out.println(RandomIndexOffirstName);
		System.out.println(RandomIndexOfLastName);
		
		WebElement FirstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput =driver.findElement(By.id("lastname"));
		WebElement emailAdressInput=driver.findElement(By.id("email_address"));
		WebElement passwordInput =driver.findElement(By.id("password"));
		WebElement confirmpasswordInput =driver.findElement(By.id("password-confirmation"));
		WebElement createAnAccountButton=driver.findElement(By.xpath("//button[@title='Create an Account']"));
		
		FirstNameInput.sendKeys(FirstName);
		lastNameInput.sendKeys(lastName);
		emailAdressInput.sendKeys(FirstName+lastName+RandomNumber+domainName);
		passwordInput.sendKeys(password);
		confirmpasswordInput.sendKeys(password);
		createAnAccountButton.click();
		emailAddressToLogin = FirstName+lastName+RandomNumber+domainName;
		WebElement massageAswebelement = driver.findElement(By.className("messages"));
		String Actualmassage = massageAswebelement.getText();
		String Expectedmassage ="Thank you for registering with Main Website Store.";
		Assert.assertEquals(Actualmassage, Expectedmassage);
		
		
	}
	
	
	@Test(priority = 2)
	public void AddmenItem () {
		WebElement menSection = driver.findElement(By.id("ui-id-5"));
		menSection.click();
		WebElement OlproductItemContainer = driver.findElement(By.className("product-items")) ; //driver--grandfather
		List<WebElement> allItems =  OlproductItemContainer.findElements(By.tagName("li"));                                //container--father
		int totalNumberOfItem = allItems.size();
		int randomItem = rand.nextInt(totalNumberOfItem);
		allItems.get(randomItem).click();
		
		WebElement menSizesContainer=driver.findElement(By.xpath("//div[@class='swatch-attribute size']")) ;
		List<WebElement> allSizes =   menSizesContainer.findElements(By.tagName("div"));
		int totalNumberOfSizes = allSizes.size();
		int randomSizes =rand.nextInt(totalNumberOfSizes);
		allSizes.get(randomSizes).click();
		
		WebElement menColorContainer=driver.findElement(By.xpath("//div[@class='swatch-attribute color']"));
		List<WebElement> allColor = menColorContainer.findElements(By.tagName("div"));
		int totalNumberOfColor =allColor.size();
		int randomColor=rand.nextInt(totalNumberOfColor);
		allColor.get(randomColor).click();
		
		WebElement addButton = driver.findElement(By.xpath("//button[@id='product-addtocart-button']"));
		addButton.click();
		WebElement massageAdded = driver.findElement(By.cssSelector(".page.messages"));
		String Actual = massageAdded.getText();
		boolean Actualmassage = Actual.contains(Actual);
		boolean Expectedmassage = true;
		Assert.assertEquals(Actualmassage, Expectedmassage);
		
	}
	
	@Test(priority = 3)
	public void LogOut() throws InterruptedException {
		driver.get(LogoutPage);
		WebElement massageAslogout = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
	    String Acutalmassage = massageAslogout.getText();
	    String Expectedmassage= "You are signed out";
	    Assert.assertEquals(Acutalmassage, Expectedmassage);
	  
		
	}
	@Test(priority = 4)
	public void logIn() {

		WebElement logInPage = driver.findElement(By.linkText("Sign In"));
		logInPage.click();
		WebElement emailInput = driver.findElement(By.id("email"));
		emailInput.sendKeys(emailAddressToLogin);
		WebElement passwordInput = driver.findElement(By.id("pass"));
		passwordInput.sendKeys(password);
		WebElement signInButton = driver.findElement(By.cssSelector(".action.login.primary"));
		signInButton.click();

		String welcomeMassage= driver.findElement(By.className("logged-in")).getText();
		boolean ActualMass= welcomeMassage.contains("Welcome");
		boolean ExpectedMass=true;
	    Assert.assertEquals(ActualMass, ExpectedMass);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}